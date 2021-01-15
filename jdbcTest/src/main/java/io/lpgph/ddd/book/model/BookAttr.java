package io.lpgph.ddd.book.model;

import io.lpgph.ddd.common.domain.AbstractValueObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** 产品属性 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = false)
@Value
@Table("jdbc_book_attr")
public class BookAttr extends AbstractValueObject {

  //  @Embedded(prefix = "prop_", onEmpty = Embedded.OnEmpty.USE_NULL)
  // PropId propId;
  Long propId;

  String name;

  @MappedCollection(idColumn = "book_attr_id")
  Set<BookAttrValue> values;

  public static BookAttr create(Long propId, String name, Set<BookAttrValue> values) {
    return new BookAttr(propId, name, values);
  }
}
