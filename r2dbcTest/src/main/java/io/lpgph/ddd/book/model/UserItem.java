package io.lpgph.ddd.book.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("jdbc_user_book")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserItem {

  private Long userId;

  private LocalDateTime gmtCreate;

  public static UserItem create(Long userId) {
    return new UserItem(userId, LocalDateTime.now());
  }
}
