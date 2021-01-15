package io.lpgph.ddd.common.domain;

/** 实体值对象基类 */
public interface Entity<T> {
  boolean sameIdentityAs(T other);
}
