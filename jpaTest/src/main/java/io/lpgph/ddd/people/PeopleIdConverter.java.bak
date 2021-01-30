package io.lpgph.ddd.people;

import io.lpgph.ddd.people.model.PeopleId;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
@Slf4j
public class PeopleIdConverter implements AttributeConverter<PeopleId, Long> {

  @Override
  public Long convertToDatabaseColumn(PeopleId id) {
    log.info("\n\nconvertToDatabaseColumn\n\n");

    return id == null ? null : id.getId();
  }

  @Override
  public PeopleId convertToEntityAttribute(Long id) {
    log.info("\n\nconvertToEntityAttribute\n\n");
    return PeopleId.create(id);
  }
}
