package io.lpgph.ddd.user.model;

import io.lpgph.ddd.common.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserEvent extends DomainEvent {

  private UserId userId;

  public UserEvent(UserId userId) {
    this.userId = userId;
  }
}
