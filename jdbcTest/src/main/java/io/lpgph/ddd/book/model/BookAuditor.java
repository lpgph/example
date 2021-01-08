package io.lpgph.ddd.book.model;

import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("jdbc_book_auditor")
@Getter
public class BookAuditor {

  private Long id;
  private final Long bookId;

  // save delete update
  private final EventTypeEnum eventType;

  private final String oldValue;
  private final String newValue;

  private final Long userId;
  private final LocalDateTime createDate;

  public BookAuditor(
      Long bookId,
      EventTypeEnum eventType,
      String oldValue,
      String newValue,
      Long userId,
      LocalDateTime createDate) {
    this.bookId = bookId;
    this.eventType = eventType;
    this.oldValue = oldValue;
    this.newValue = newValue;
    this.userId = userId;
    this.createDate = createDate;
  }
}
