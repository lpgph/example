package io.lpgph.ddd.book.model;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

/** 产品属性 */
@Value
@Table("jdbc_book_attr_value")
public class BookAttrValue {

  @Embedded(prefix = "prop_value_", onEmpty = Embedded.OnEmpty.USE_NULL)
  PropValueId propValueId;

  public static BookAttrValue create(PropValueId propValueId) {
    return new BookAttrValue(propValueId);
  }
}
