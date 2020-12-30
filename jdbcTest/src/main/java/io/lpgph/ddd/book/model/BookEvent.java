package io.lpgph.ddd.book.model;

import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.user.model.UserId;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public abstract class BookEvent extends DomainEvent {

  private Long bookId;

}
