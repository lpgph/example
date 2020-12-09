package io.lpgph.ddd.user;

import io.lpgph.ddd.user.model.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigInteger;

@WritingConverter
public class UserIdToLongConverter implements Converter<UserId, Long> {

  @Override
  public Long convert(UserId source) {
    return source.getId();
  }
}
