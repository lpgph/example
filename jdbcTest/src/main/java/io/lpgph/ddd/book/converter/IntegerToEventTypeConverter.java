package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.book.constant.e.EventTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@Slf4j
@ReadingConverter
public class IntegerToEventTypeConverter implements Converter<Integer, EventTypeEnum> {
  @Override
  public EventTypeEnum convert(Integer source) {
    log.info("IntegerToEventTypeConverter ");
    return EventTypeEnum.resolve(source);
  }
}

