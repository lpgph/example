package io.lpgph.ddd.book.model;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Table;

/** 产品属性 */
@Value
@Table("jdbc_book_attr_value")
public class BookAttrValue {

  Long attrValueId;

  String name;
}
