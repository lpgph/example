package io.lpgph.ddd.user.model;

import lombok.*;

// @ToString
// @EqualsAndHashCode
// @NoArgsConstructor(access = AccessLevel.PACKAGE)
// @Getter
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
public class UserId {

  Long id;

  public static UserId create(Long id) {
    return new UserId(id);
  }

  UserId withId( Long id){
    return UserId.create(id);
  }

  @Override
  public String toString() {
    return String.valueOf(id);
  }
}
