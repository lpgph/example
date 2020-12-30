package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.UserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigInteger;

@Slf4j
@ReadingConverter
public class LongToUserIdConverter implements Converter<BigInteger, UserId> {

  @Override
  public UserId convert(BigInteger source) {
    return UserId.create(source.longValue());
  }
}
