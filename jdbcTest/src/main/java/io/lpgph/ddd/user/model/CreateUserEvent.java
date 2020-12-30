package io.lpgph.ddd.user.model;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Value
public class CreateUserEvent extends UserEvent {
  String name;

  public CreateUserEvent(UserId userId, String name) {
    super(userId);
    this.name = name;
  }
}
