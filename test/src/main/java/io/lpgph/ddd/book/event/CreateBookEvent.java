package io.lpgph.ddd.book.event;

import io.lpgph.ddd.common.domain.DomainEvent;
import lombok.*;

// @JsonTypeName(value = "input")
@AllArgsConstructor
@Value
public class CreateBookEvent implements DomainEvent {
  Long bookId;
  String name;
}
