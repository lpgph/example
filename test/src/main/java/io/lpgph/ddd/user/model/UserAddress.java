package io.lpgph.ddd.user.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Table("jdbc_user_address")
public class UserAddress {
  String tag, address;
}
