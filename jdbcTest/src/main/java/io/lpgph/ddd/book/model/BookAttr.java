package io.lpgph.ddd.book.model;

import io.lpgph.ddd.common.domain.Identified;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** 产品属性 */
@EqualsAndHashCode(callSuper = false)
@Value
@Table("jdbc_book_attr")
public class BookAttr extends Identified {

  //  @Embedded(prefix = "prop_", onEmpty = Embedded.OnEmpty.USE_NULL)
  // PropId propId;
  Long propId;

  String name;

  @MappedCollection(idColumn = "book_attr_id")
  Set<BookAttrValue> values;
}
