package io.lpgph.ddd.common;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class AbstractDomainEvent2 {

  private final String eventId = UUID.randomUUID().toString().replaceAll("-", "");

  private final LocalDateTime gmtCreate = LocalDateTime.now();
}
