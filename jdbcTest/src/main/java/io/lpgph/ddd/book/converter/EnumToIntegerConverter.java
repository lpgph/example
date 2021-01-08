package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.book.model.EventTypeEnum;
import io.lpgph.ddd.common.BaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@Slf4j
@WritingConverter
public class EnumToIntegerConverter implements Converter<BaseEnum, Integer> {
  @Override
  public Integer convert(BaseEnum source) {
    log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    return source.getValue();
  }
}
