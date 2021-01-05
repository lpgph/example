package io.lpgph.ddd.user.model;

import lombok.*;

/** 产品属性 */
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
public class UserProp {

  Boolean isVip;

  Integer level;
}
