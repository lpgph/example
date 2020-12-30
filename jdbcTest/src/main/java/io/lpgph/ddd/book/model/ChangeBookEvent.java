package io.lpgph.ddd.book.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Value
public class ChangeBookEvent extends BookEvent {
  String name;

  public ChangeBookEvent(Long bookId, String name) {
    super(bookId);
    this.name = name;
  }
}
