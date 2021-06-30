package io.lpgph.ddd.user.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

/** 产品属性 */
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
public class UserProp {

  @Column("is_vip")
  Boolean vip;

  Integer level;
}
