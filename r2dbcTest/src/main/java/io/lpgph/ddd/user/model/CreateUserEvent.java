package io.lpgph.ddd.user.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateUserEvent extends UserEvent {
  private String name;

  public CreateUserEvent(UserId userId, String name) {
    super(userId);
    this.name = name;
  }
}
