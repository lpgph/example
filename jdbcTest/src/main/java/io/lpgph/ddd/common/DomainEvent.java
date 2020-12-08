package io.lpgph.ddd.common;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
@Getter
public abstract class DomainEvent {

  private final String eventId = UUID.randomUUID().toString().replaceAll("-", "");

  private final LocalDateTime gmtCreate = LocalDateTime.now();
}
