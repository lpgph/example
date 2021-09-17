package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.common.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@Slf4j
@ReadingConverter
public class IntegerToStateConverter implements Converter<Integer, StateEnum> {
  @Override
  public StateEnum convert(Integer source) {
    log.info("IntegerToStateConverter ");
    return StateEnum.resolve(source);
  }
}



