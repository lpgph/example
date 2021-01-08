package io.lpgph.ddd.book.converter;

import io.lpgph.ddd.common.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@Slf4j
@WritingConverter
public class StateToIntegerConverter implements Converter<Enum<StateEnum>,Integer> {
    @Override
    public Integer convert(Enum<StateEnum> source) {
        log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return ((StateEnum) source).getValue();
    }
}
