package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.utils.json.JsonUtil;
import io.lpgph.ddd.utils.json.TypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class JsonToStringArrayConverter implements Converter<String, String[]> {

  @Override
  public String[] convert(String source) {
    return JsonUtil.fromJson(source, new TypeReference<>() {});
  }
}
