package io.lpgph.ddd.user.model;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE) // jackson 反序列化需要默认构造函数
@AllArgsConstructor
@Value
public class CreateUserEvent extends UserEvent {
  String name;

  public CreateUserEvent(UserId userId, String name) {
    super(userId);
    this.name = name;
  }
}
