package io.lpgph.ddd.common;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class AbstractDomainEvent<R extends AggregationRootId> implements DomainEvent<R> {

  private final String eventId = UUID.randomUUID().toString().replaceAll("-", "");

  private final LocalDateTime gmtCreate = LocalDateTime.now();

  private R rootId;

  @Override
  public R getRootId() {
    return rootId;
  }

  @Override
  public void setRootId(R rootId) {
    this.rootId = rootId;
  }
}
