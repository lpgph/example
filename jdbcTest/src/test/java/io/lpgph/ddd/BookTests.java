package io.lpgph.ddd;

import io.lpgph.ddd.book.model.*;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class BookTests {

  @Autowired private BookRepo bookRepo;

  @Test
  void query() {
    List<Book> bookList = bookRepo.findAllByNameLike("%飘%");
    if (bookList != null) {
      log.info("\n\n\n{}", bookList.size());
      bookList.forEach(b -> log.info("{}", JsonUtil.toJson(b)));
      log.info("\n\n\n");
    }
  }

  @Test
  void create() {
    Book book = new Book("<飘>");
    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }

  @Test
  void create2() {
    Book book = new Book("<飘333>", "book", "hhh");
    BookAttr attr = new BookAttr(1L, "属性1");
    book.addAttr(attr);

    book.change(new BookAd(true, false));
//    book.change(List.of(new BookPrice(1, 100L), new BookPrice(2, 200L)));

    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }

  @Test
  void create3() {
    Book book = new Book("<飘2222>");
    BookAttr attr = new BookAttr(1L, "属性1");
    attr.addValue(new BookAttrValue(1L, "value1"));
    book.addAttr(attr);
    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }
}
