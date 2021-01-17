package io.lpgph.ddd.book.model;

import lombok.Value;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;

@Value
public class BookInfo {

  String author;

  @MappedCollection(idColumn = "book_id")
  Set<Tag> tags;
}
