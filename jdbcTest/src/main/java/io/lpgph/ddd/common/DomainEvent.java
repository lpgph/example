package io.lpgph.ddd.common;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
// @JsonSubTypes({
//  @JsonSubTypes.Type(value = UserEvent.class, name = "UserEvent"),
//  @JsonSubTypes.Type(value = CreateUserEvent.class, name = "CreateUserEvent"),
// })
//  @JsonSubTypes.Type(value = BookEvent.class)
@Getter
public abstract class DomainEvent {

  private final String eventId = UUID.randomUUID().toString().replaceAll("-", "");

  private final LocalDateTime gmtCreate = LocalDateTime.now();

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "[" + eventId + "]";
  }
}
