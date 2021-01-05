package io.lpgph.ddd.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter(value = AccessLevel.PROTECTED)
@Getter(value = AccessLevel.PROTECTED)
public abstract class Identified {

    @Id
    private Long id;

}
