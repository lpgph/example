package io.lpgph.ddd.user.model;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Table;

@Table("jdbc_user_tag")
@Value
public class UserTag {
  String name;

  public static UserTag create(String name) {
    return new UserTag(name);
  }
}
