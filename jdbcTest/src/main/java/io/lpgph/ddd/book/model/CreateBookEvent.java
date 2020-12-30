package io.lpgph.ddd.book.model;

import lombok.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Value
public class CreateBookEvent extends BookEvent {
  String name;

  public CreateBookEvent(Long bookId, String name) {
    super(bookId);
    this.name = name;
  }
}
