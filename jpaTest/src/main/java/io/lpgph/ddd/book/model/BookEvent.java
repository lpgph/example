package io.lpgph.ddd.book.model;

import io.lpgph.ddd.common.AbstractDomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BookEvent extends AbstractDomainEvent {

  private Long bookId;

  public BookEvent(Long bookId) {
    this.bookId = bookId;
  }
}
