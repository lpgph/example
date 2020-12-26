package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.book.model.BookAd;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class JsonToBookAdConverter implements Converter<String, BookAd> {

  @Override
  public BookAd convert(String source) {
    System.out.println("转换JSON  " + source);
    return JsonUtil.fromJson(source, BookAd.class);
  }
}
