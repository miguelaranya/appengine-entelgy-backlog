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

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.appengine.api.users.User;
import com.google.appengine.repackaged.com.google.gson.JsonElement;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 14/02/2018.
 *
 * @author Entelgy
 */
public class Profile implements Serializable {

  private static final long serialUID = 1L;

  private String algorithm;

  private String keyId;

  private String issuer;

  private String domainSubject;

  private String subject;

  private Long expiresAt;

  private Long issuedAt;

  private String audit;

  private String domain;

  private String hash;

  public String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }

  public String getKeyId() {
    return keyId;
  }

  public void setKeyId(String keyId) {
    this.keyId = keyId;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getDomainSubject() {
    return domainSubject;
  }

  public void setDomainSubject(String domainSubject) {
    this.domainSubject = domainSubject;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Long getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Long expiresAt) {
    this.expiresAt = expiresAt;
  }

  public Long getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(Long issuedAt) {
    this.issuedAt = issuedAt;
  }

  public String getAudit() {
    return audit;
  }

  public void setAudit(String audit) {
    this.audit = audit;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public static Profile getProfile(DecodedJWT jwt) {
    Profile profile = new Profile();
    profile.setAlgorithm(jwt.getAlgorithm());
    profile.setKeyId(jwt.getKeyId());
    profile.setIssuer(jwt.getIssuer());
    profile.setSubject(jwt.getSubject());
    if (profile.getSubject().contains(":")) {
      String[] subjects = profile.getSubject().split(":");
      profile.setDomainSubject(subjects[0]);
      profile.setSubject(subjects[1]);
    }
    profile.setExpiresAt(jwt.getExpiresAt().getTime());
    profile.setIssuedAt(jwt.getIssuedAt().getTime());
    profile.setAudit(jwt.getClaim("aud").asString());
    profile.setDomain(jwt.getClaim("hd").asString());
    profile.setHash(jwt.getClaim("at_hash").asString());
    return profile;
  }
}
