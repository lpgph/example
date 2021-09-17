package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.common.BaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@Slf4j
@WritingConverter
public class IntegerToEnumConverter<T extends BaseEnum> implements Converter<Integer, BaseEnum> {

  private final Class<T> enumClass;

  public IntegerToEnumConverter(Class<T> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public T convert(Integer source) {
    log.info("IntegerToEnumConverter  {}",enumClass.getName());
    for (T constant : enumClass.getEnumConstants()) {
      if (constant.getValue() == source) return constant;
    }
    throw new IllegalArgumentException("Unable to find Enum type for : " + source);
  }

}
