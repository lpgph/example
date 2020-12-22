package io.lpgph.oidc.common.bean;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class LoginInfo {
  private String username;
  private String password;
}
