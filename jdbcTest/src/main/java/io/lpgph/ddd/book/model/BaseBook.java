package io.lpgph.ddd.book.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Embedded;

@Setter(value = AccessLevel.PROTECTED)
@Getter
public abstract class BaseBook {

    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private BookInfo info;
}
