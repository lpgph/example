package io.lpgph.ddd.event.converter;

import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class StringToDomainEventConverter implements Converter<String, DomainEvent> {

  @Override
  public DomainEvent convert(String source) {
    return JsonUtil.fromJson(source, DomainEvent.class);
  }
}
