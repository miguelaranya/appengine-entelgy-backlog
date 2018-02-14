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

package com.appengine.entelgy.backlog.bean;

import com.google.appengine.api.users.User;

/**
 * Created on 14/02/2018.
 *
 * @author Entelgy
 */
public class Profile {

  private String username;

  private String email;

  private String domain;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public static final Profile getProfile(User user) {
    Profile profile = new Profile();
    profile.setUsername(user.getNickname());
    profile.setEmail(user.getEmail());
    profile.setDomain(user.getAuthDomain());
    return profile;
  }
}
