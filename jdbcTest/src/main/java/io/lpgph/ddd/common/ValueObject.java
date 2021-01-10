package io.lpgph.ddd.common;

public interface ValueObject<T> {

  boolean sameValueAs(T other);
}
