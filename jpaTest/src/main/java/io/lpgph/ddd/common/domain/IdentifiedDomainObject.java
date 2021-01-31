package io.lpgph.ddd.common.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Setter(value = AccessLevel.PROTECTED)
@Getter(value = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class IdentifiedDomainObject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;
}
