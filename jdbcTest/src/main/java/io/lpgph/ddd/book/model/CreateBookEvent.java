package io.lpgph.ddd.book.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateBookEvent extends BookEvent {
  private String name;

  public CreateBookEvent(Long bookId, String name) {
    super(bookId);
    this.name = name;
  }
}
