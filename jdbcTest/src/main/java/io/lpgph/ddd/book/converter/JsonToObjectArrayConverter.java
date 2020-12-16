package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.book.model.BookPrice;
import io.lpgph.ddd.utils.json.JsonUtil;
import io.lpgph.ddd.utils.json.TypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.List;

@ReadingConverter
public class JsonToObjectArrayConverter implements Converter<String, List<BookPrice>> {

  @Override
  public List<BookPrice> convert(String source) {
    return JsonUtil.fromJson(source, new TypeReference<List<BookPrice>>() {});
  }
}
