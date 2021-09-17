package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.UserAddress;
import io.lpgph.ddd.utils.json.JsonUtil;
import io.lpgph.ddd.utils.json.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@Slf4j
@ReadingConverter
public class StringToUserAddressConverter implements Converter<String, UserAddress> {

  @Override
  public UserAddress convert(String source) {
    log.info("StringToUserAddressConverter  {}", source);
    return JsonUtil.fromJson(source, UserAddress.class);
  }
}
