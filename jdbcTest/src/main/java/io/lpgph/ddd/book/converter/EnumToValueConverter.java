package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.common.BaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * TODO 妥协 spring data jdbc 枚举默认使用枚举名称 即 枚举转换字符串 如果需要自定义枚举值 枚举值类型必须为字符串
 *
 * <p>https://github.com/spring-projects/spring-data-jdbc/issues/629
 *
 * @see org.springframework.core.convert.support.EnumToStringConverter
 * @see org.springframework.data.jdbc.core.convert.JdbcColumnTypes
 */
@Slf4j
@WritingConverter
public class EnumToValueConverter implements Converter<BaseEnum, String> {
  @Override
  public String convert(BaseEnum source) {
    return String.valueOf(source.getValue());
  }
}
