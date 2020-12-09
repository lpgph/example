package io.lpgph.ddd.user;

import io.lpgph.ddd.user.model.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigInteger;

@ReadingConverter
public class LongToUserIdConverter implements Converter<Long, UserId> {

  @Override
  public UserId convert(Long source) {
    return UserId.create(source);
  }
}
