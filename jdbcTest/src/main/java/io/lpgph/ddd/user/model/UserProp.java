package io.lpgph.ddd.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 产品属性 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserProp {

  private Boolean isVip;

  private Integer level;
}
