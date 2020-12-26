package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.book.model.BookAd;
import io.lpgph.ddd.utils.json.JsonUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.convert.JdbcValue;

@WritingConverter
public class BookAdToJsonConverter implements Converter<BookAd, String> {

  @Override
  public String convert(BookAd source) {
    return JsonUtil.toJson(source);
  }
}
