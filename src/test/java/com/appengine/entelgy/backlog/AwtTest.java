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

import static org.junit.Assert.assertNotNull;

import com.appengine.entelgy.backlog.bean.Profile;
import com.auth0.jwt.JWT;
import org.junit.Before;
import org.junit.Test;

/**
 * Created on 14/02/2018.
 *
 * @author Entelgy
 */
public class AwtTest {

  String token;

  @Before
  public void setUp() {
    token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImUyNzY3MWQ3M2EyNjA1Y2NkNDU0NDEzYzRjOTRlMjViM2Y2NmNkZWEifQ"
            + ".eyJhenAiOiI2MTMzOTI2NzkxMy0zZjFwcHZybnBjdjZudGc1bDcxMHA2cDZ2c3R0MGZhMi5hcHBz"
            + "Lmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6IjYxMzM5MjY3OTEzLTNmMXBwdnJucGN2Nm50Z"
            + "zVsNzEwcDZwNnZzdHQwZmEyLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTE1MTE"
            + "zNzQyNTY4NTQ1MjcyNzYwIiwiaGQiOiJlbnRlbGd5LmNvbSIsImVtYWlsIjoibWlndWVsLmFyYW55Y"
            + "UBlbnRlbGd5LmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoiUUQ2NW1PalZyZWZ"
            + "RYklqVlZCclY5USIsImV4cCI6MTUxODY0MDM0MCwiaXNzIjoiaHR0cHM6Ly9hY2NvdW50cy5nb29nb"
            + "GUuY29tIiwiaWF0IjoxNTE4NjM2NzQwfQ.EwMpi38jiJ4R-xsMesp9SNCXi5K3PRLfv1ezzmxSebuQ"
            + "eAqfOuCp7lqIH1nPEv2EyBqIKhAF7lDZZnChRVqnIqzzocfbARsqO56Rv_GeNA1MwML4MiNFivDZ_R"
            + "50TAs8zee8HQ6NHs8Y7ZEUX9lyZ00kBrV8lwuuwjm-pA7OumPLUo4b-PO5Ez3nNmP9BlWCac3gAcOb"
            + "2Q1MIZzcb7aV74xjXewVy6e7hpqIGfIvSyhLYcB9hjrGrKBy-RXCy9DPXnIgjbfXETGfC5vgI1B52e"
            + "W2UnXicSSYsWZ2FR0zkuPabA2JTwxCFvvLNvu-3dFMxE8xMYBUC9P8sRIxPC7IOw";
  }

  @Test
  public void isTokenTest() {
    Profile profile = Profile.getProfile(JWT.decode(token));

    assertNotNull(profile);
    assertNotNull(profile.getAlgorithm());
    assertNotNull(profile.getKeyId());
    assertNotNull(profile.getIssuer());
    assertNotNull(profile.getSubject());
    assertNotNull(profile.getExpiresAt());
    assertNotNull(profile.getIssuedAt());
    assertNotNull(profile.getAudit());
    assertNotNull(profile.getDomain());
    assertNotNull(profile.getHash());
  }
}
