package io.lpgph.ddd.user.model;

import io.lpgph.ddd.common.AggregationRootId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserId implements AggregationRootId {

  private final Long id;

  public static UserId create(Long id) {
    return new UserId(id);
  }
}
