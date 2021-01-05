package io.lpgph.ddd.book.model;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface BookRepo2 extends Repository<Book, BookId> {

  Optional<Book> findByBookId(BookId bookId);

  void save(Book book);

  List<Book> findAllByNameLike(String name);

  @Query("select * from Book where name like concat(:name, '%')")
  List<Book> listAllByName(String name);

//  List<BookId> findBookIdAll();
}
