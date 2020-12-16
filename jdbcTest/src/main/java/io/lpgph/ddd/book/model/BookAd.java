package io.lpgph.ddd.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 产品属性 */
@AllArgsConstructor
@Setter
@Getter
public class BookAd {

  private Boolean isVip;

  private Boolean isShow;
}
