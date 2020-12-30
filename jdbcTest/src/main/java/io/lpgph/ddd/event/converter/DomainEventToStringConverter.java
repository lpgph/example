package io.lpgph.ddd.event.converter;

import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class DomainEventToStringConverter implements Converter<DomainEvent, String> {

  @Override
  public String convert(DomainEvent source) {
    return JsonUtil.toJson(source);
  }
}
