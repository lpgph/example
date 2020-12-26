package io.lpgph.ddd.book.model;

import lombok.*;

import java.lang.annotation.Documented;

/** 产品属性 */
@Value
public class BookAd {

  Boolean isVip;

  Boolean isShow;
}
