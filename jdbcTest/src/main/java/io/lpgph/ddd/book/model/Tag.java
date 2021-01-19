package io.lpgph.ddd.book.model;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Table("jdbc_book_tag")
public class Tag {
  String name;

  public static Tag create(String name) {
    return new Tag(name);
  }
}
