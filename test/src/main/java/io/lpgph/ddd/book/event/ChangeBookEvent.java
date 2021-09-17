package io.lpgph.ddd.book.event;

import io.lpgph.ddd.common.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class ChangeBookEvent implements DomainEvent {

   Long bookId;
  String name;

}
