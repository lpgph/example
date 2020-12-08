package io.lpgph.ddd.book.model;

import io.lpgph.ddd.common.AbstractDomainEvent2;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BookEvent extends AbstractDomainEvent2 {

  private Long bookId;

  public BookEvent(Long bookId) {
    this.bookId = bookId;
  }
}
