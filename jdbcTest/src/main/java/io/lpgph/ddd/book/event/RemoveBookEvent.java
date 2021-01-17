package io.lpgph.ddd.book.event;

import io.lpgph.ddd.common.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@AllArgsConstructor
@Value
public class RemoveBookEvent implements DomainEvent {
  Long bookId;
}
