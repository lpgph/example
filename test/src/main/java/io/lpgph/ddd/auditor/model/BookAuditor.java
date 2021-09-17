package io.lpgph.ddd.auditor.model;

import io.lpgph.ddd.book.constant.e.EventTypeEnum;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("jdbc_book_auditor")
@Builder
@Getter
public class BookAuditor {

  @Id
  private Long id;

  private Long bookId;

  // save delete update
  private EventTypeEnum eventType;

  private String oldValue;
  private String newValue;

  private Long userId;
  private LocalDateTime createDate;

  public static BookAuditor create(
      Long bookId,
      EventTypeEnum eventType,
      String oldValue,
      String newValue,
      Long userId,
      LocalDateTime createDate) {
    return BookAuditor.builder()
        .bookId(bookId)
        .eventType(eventType)
        .oldValue(oldValue)
        .newValue(newValue)
        .userId(userId)
        .createDate(createDate)
        .build();
  }
}
