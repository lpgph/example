package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.UserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigInteger;

@Slf4j
@WritingConverter
public class UserIdToLongConverter implements Converter<UserId, BigInteger> {

  @Override
  public BigInteger convert(UserId source) {
    return BigInteger.valueOf(source.getId());
  }
}
