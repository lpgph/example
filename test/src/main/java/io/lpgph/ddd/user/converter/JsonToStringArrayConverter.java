package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.utils.json.JsonUtil;
import io.lpgph.ddd.utils.json.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@Slf4j
@ReadingConverter
public class JsonToStringArrayConverter implements Converter<String, String[]> {

  @Override
  public String[] convert(String source) {
    log.info("JsonToStringArrayConverter  {}", source);
    return JsonUtil.fromJson(source, new TypeReference<>() {});
  }
}
