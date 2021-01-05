package io.lpgph.ddd.user.model;

import lombok.*;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
public class UserAddress {
  String tag, address;
}
