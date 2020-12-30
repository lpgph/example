package io.lpgph.ddd.user.model;

import lombok.Value;

@Value
public class UserId {

  Long id;

  public static UserId create(Long id) {
    return new UserId(id);
  }
}
