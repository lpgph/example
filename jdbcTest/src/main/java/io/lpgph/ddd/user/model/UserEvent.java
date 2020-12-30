package io.lpgph.ddd.user.model;

import io.lpgph.ddd.common.DomainEvent;
import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class UserEvent extends DomainEvent {

  private UserId userId;
}
