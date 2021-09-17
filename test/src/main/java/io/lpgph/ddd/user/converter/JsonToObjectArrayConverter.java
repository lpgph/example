package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.UserAddress;
import io.lpgph.ddd.user.model.UserProp;
import io.lpgph.ddd.utils.json.JsonUtil;
import io.lpgph.ddd.utils.json.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.List;
import java.util.Set;

@Slf4j
@ReadingConverter
public class JsonToObjectArrayConverter implements Converter<String, UserAddress[]> {

  @Override
  public UserAddress[] convert(String source) {
    log.info("JsonToObjectArrayConverter  {}", source);
    return JsonUtil.fromJson(source, new TypeReference<>() {});
  }
}
