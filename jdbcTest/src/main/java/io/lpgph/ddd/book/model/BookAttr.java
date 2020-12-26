package io.lpgph.ddd.book.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

/** 产品属性 */
@NoArgsConstructor
@Setter
@Getter
@Table("jdbc_book_attr")
public class BookAttr {

  @Id
  Long id;

  Long attrId;

  private String name;

//  @MappedCollection(idColumn = "attr_id")
//  private Set<BookAttrValue> values = new HashSet<>();

  public BookAttr(Long attrId, String name) {
    this.attrId = attrId;
    this.name = name;
//    this.values = new HashSet<>();
  }

//  public void addValue(BookAttrValue value) {
//    if (this.values == null) this.values = new HashSet<>();
//    this.values.add(value);
//  }

}
