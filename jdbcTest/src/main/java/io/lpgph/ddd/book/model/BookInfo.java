package io.lpgph.ddd.book.model;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Value
@Table("jdbc_book_info")
public class BookInfo {

  String ad;

  @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
  Monetary price;

  @MappedCollection(idColumn = "book_id")
  Set<Tag> tags;

  public static BookInfo create(String ad, Monetary price, Set<Tag> tags) {
    return new BookInfo(ad, price, tags);
  }
}
