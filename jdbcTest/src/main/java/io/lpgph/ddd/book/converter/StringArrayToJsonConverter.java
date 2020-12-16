package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class StringArrayToJsonConverter implements Converter<String[], String> {

  @Override
  public String convert(String[] source) {
    return JsonUtil.toJson(source);
  }
}
