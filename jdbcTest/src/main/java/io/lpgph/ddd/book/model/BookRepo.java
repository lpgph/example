package io.lpgph.ddd.book.model;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {

  List<Book> findAllByNameLike(String name);

  @Query("select * from jdbc_book  where name like concat( '%',:name, '%')")
  List<Book> listAllByName(String name);

  @Query("select * from jdbc_book  where ad like concat( '%',:keyword, '%')")
  List<Book> listAllByInfo(String keyword);

  @Query("select * from Book  where Book.info.ad like concat( '%',:keyword, '%')")
  List<Book> listAllByInfo2(String keyword);
}
