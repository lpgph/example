package io.lpgph.ddd.user.converter;

import io.lpgph.ddd.user.model.UserProp;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class JsonToUserPropConverter implements Converter<String, UserProp> {

  @Override
  public UserProp convert(String source) {
    System.out.println("转换JSON  " + source);
    return JsonUtil.fromJson(source, UserProp.class);
  }
}
