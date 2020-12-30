package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.CreateUserEvent;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class StringToCreateUserEventConverter implements Converter<String, CreateUserEvent> {
  @Override
  public CreateUserEvent convert(String source) {
    return JsonUtil.fromJson(source, CreateUserEvent.class);
  }
}
