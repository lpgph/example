package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.CreateUserEvent;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class CreateUserEventToStringConverter implements Converter<CreateUserEvent, String> {

  @Override
  public String convert(CreateUserEvent source) {
    return JsonUtil.toJson(source);
  }
}
