package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.book.model.EventTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@Slf4j
@WritingConverter
public class EventTypeToIntegerConverter implements Converter<EventTypeEnum, Integer> {
  @Override
  public Integer convert(EventTypeEnum source) {
    log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    return source.getValue();
  }
}
