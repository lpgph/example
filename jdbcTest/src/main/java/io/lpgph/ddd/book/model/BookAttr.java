package io.lpgph.ddd.book.model;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** 产品属性 */
@Value
@Table("jdbc_book_attr")
public class BookAttr {

  //  @Embedded(prefix = "prop_", onEmpty = Embedded.OnEmpty.USE_NULL)
  // PropId propId;

  @Id Long id;

  Long propId;

  String name;

  @MappedCollection(idColumn = "book_attr_id")
  Set<BookAttrValue> values;

  public static BookAttr create(Long id, Long propId, String name, Set<BookAttrValue> values) {
    return new BookAttr(id, propId, name, values);
  }
}
