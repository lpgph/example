package io.lpgph.ddd.user;

import io.lpgph.ddd.user.model.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigInteger;

@WritingConverter
public class UserIdToLongConverter implements Converter<UserId, BigInteger> {

  @Override
  public BigInteger convert(UserId source) {
    return source == null ? null : BigInteger.valueOf(source.getId());
  }
}
