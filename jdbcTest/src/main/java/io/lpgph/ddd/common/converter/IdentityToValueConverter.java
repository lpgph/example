package io.lpgph.ddd.common.converter;

import io.lpgph.ddd.common.Identity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@Slf4j
@WritingConverter
public class IdentityToValueConverter implements Converter<Identity<?>, Object> {
  @Override
  public Object convert(Identity source) {
    return source.id();
  }
}
