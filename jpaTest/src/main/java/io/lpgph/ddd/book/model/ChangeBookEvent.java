package io.lpgph.ddd.book.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ChangeBookEvent extends BookEvent {
  private String name;

  public ChangeBookEvent(Long bookId, String name) {
    super(bookId);
    this.name = name;
  }
}
