package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.UserProp;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class UserPropToStringConverter implements Converter<UserProp, String> {

  @Override
  public String convert(UserProp source) {
    return JsonUtil.toJson(source);
  }
}
