package io.lpgph.ddd.book.model;

import io.lpgph.ddd.common.Identity;
import io.lpgph.ddd.common.ValueObject;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Value
public class BookId implements ValueObject<BookId>, Identity<Long> {

  @NonNull Long id;

  public static BookId create(final Long id) {
    return new BookId(id);
  }

  @Override
  public Long id() {
    return this.id;
  }

  @Override
  public boolean sameValueAs(BookId other) {
    return other != null && Objects.equals(this.id, other.id);
  }
}
