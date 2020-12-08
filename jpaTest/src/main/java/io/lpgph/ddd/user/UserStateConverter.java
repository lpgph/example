package io.lpgph.ddd.user;

import io.lpgph.ddd.common.StateEnum;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
@Slf4j
public class UserStateConverter implements AttributeConverter<StateEnum, Integer> {

  @Override
  public Integer convertToDatabaseColumn(StateEnum attribute) {
    log.info("\n\nUserStateConverter  db \n\n");
    return attribute == null ? null : attribute.getValue();
  }

  @Override
  public StateEnum convertToEntityAttribute(Integer dbData) {
    log.info("\n\nUserStateConverter  entity \n\n");
    return StateEnum.resolve(dbData);
  }
}
