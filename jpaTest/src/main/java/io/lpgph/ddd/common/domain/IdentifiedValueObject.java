package io.lpgph.ddd.common.domain;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/** 适用于复杂值对象 值对象中包含集合 eg: 商品中有商品属性值对象 商品属性包含属性值值对象 */
@MappedSuperclass
public abstract class IdentifiedValueObject extends IdentifiedDomainObject implements Serializable {

  public IdentifiedValueObject() {
    super();
  }
}
