package io.lpgph.ddd.user.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateUserEvent extends UserEvent {
  private String name;

}
