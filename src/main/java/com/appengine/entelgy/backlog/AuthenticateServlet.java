/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appengine.entelgy.backlog;

import com.appengine.entelgy.backlog.bean.Profile;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.appengine.repackaged.com.google.api.client.json.jackson2.JacksonFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Identity Aware Proxy (IAP) Test application to reflect jwt token issued by IAP.
 * IAP must be enabled on application.
 * {@see https://cloud.google.com/iap/docs/app-engine-quickstart}
 */
@SuppressWarnings("serial")
public class AuthenticateServlet extends HttpServlet {

  private static final String IAP_JWT_HEADER = "x-goog-iap-jwt-assertion";
  private static final String IAP_AUTHENTICATED_USER_HEADER = "x-goog-authenticated-user-jwt";
  private static final String CLIENT_ID =
          "61339267913-3f1ppvrnpcv6ntg5l710p6p6vstt0fa2.apps.googleusercontent.com";

  private static final JacksonFactory jacksonFactory = new JacksonFactory();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //response.getWriter().write(request.getHeader(IAP_JWT_HEADER));

    String token = request.getHeader(IAP_JWT_HEADER);
    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
            .Builder(UrlFetchTransport.getDefaultInstance(), jacksonFactory)
            .setAudience(Collections.singletonList(CLIENT_ID))
            .setIssuer("accounts.google.com")
            .build();

    try {
      UserService userService = UserServiceFactory.getUserService();
      GoogleIdToken tokenId = verifier.verify(token);

      if (tokenId != null) {
        GoogleIdToken.Payload payload = tokenId.getPayload();

        Profile profile = new Profile();
        profile.setUserId(userService.getCurrentUser().getUserId());
        profile.setUsername((String) payload.get("given_name"));
        profile.setNickname((String) payload.get("name"));
        profile.setEmail(payload.getEmail());
        profile.setPicture((String) payload.get("picture"));

        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(profile));
      }
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }
  }
}
