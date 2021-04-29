package io.lpgph.uaa.security;

import lombok.Getter;

import java.util.Set;

@Getter
public class UserInfo {

  private Long id;
  private String username;
  private String password;
  private Set<String> authorities;
}
