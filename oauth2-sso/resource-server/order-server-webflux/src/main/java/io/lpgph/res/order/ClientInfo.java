package io.lpgph.res.order;

import lombok.Value;

import java.util.Set;

@Value
public class ClientInfo {

  Long id;
  String username;
  String password;
  Set<String> authorities;
}
