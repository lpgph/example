package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@Slf4j
@WritingConverter
public class StringArrayToJsonConverter implements Converter<String[], String> {

  @Override
  public String convert(String[] source) {
    log.info("StringArrayToJsonConverter  {}", JsonUtil.toJson(source));
    return JsonUtil.toJson(source);
  }
}
