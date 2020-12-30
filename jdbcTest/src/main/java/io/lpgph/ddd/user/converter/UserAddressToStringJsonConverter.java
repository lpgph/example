package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.UserAddress;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@Slf4j
@WritingConverter
public class UserAddressToStringJsonConverter implements Converter<UserAddress, String> {

  @Override
  public String convert(UserAddress source) {
    log.info("UserAddressToStringJsonConverter  {}", source);
    return JsonUtil.toJson(source);
  }
}
