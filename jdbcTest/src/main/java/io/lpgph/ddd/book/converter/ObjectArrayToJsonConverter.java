package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.book.model.BookPrice;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.util.List;

@WritingConverter
public class ObjectArrayToJsonConverter implements Converter<List<BookPrice>, String> {

  @Override
  public String convert(List<BookPrice> source) {
    return JsonUtil.toJson(source);
  }
}
