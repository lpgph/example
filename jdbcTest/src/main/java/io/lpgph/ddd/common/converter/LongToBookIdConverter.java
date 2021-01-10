package io.lpgph.ddd.common.converter;

import io.lpgph.ddd.book.model.BookId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@Slf4j
@ReadingConverter
public class LongToBookIdConverter implements Converter<Long, BookId> {
  @Override
  public BookId convert(Long source) {
    log.info("IntegerToEventTypeConverter ");
    return BookId.create(source);
  }
}
