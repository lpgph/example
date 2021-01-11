package io.lpgph.ddd.book.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Value
public class RemoveBookEvent extends BookEvent {
  public RemoveBookEvent(Long bookId) {
    super(bookId);
  }
}
