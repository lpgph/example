package io.lpgph.ddd;

import io.lpgph.ddd.book.model.Book;
import io.lpgph.ddd.book.model.BookRepo;
import io.lpgph.ddd.book.model.UserItem;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class BookTests {

  @Autowired private BookRepo bookRepo;


  @Test
  void query(){
    List<Book> bookList = bookRepo.findAllByNameLike("%飘%");
    if (bookList!=null){
      log.info("\n\n\n{}",bookList.size());
      bookList.forEach(b-> log.info("{}",JsonUtil.toJson(b)));
      log.info("\n\n\n");
    }
  }

  @Test
  void create()  {
    Book book = new Book("<飘>");
    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }

}
