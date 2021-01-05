package io.lpgph.ddd;

import io.lpgph.ddd.book.BookService;
import io.lpgph.ddd.book.model.Book;
import io.lpgph.ddd.book.model.BookId;
import io.lpgph.ddd.book.model.BookRepo;
import io.lpgph.ddd.book.model.BookRepo2;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@SpringBootTest
class Book2Tests {

  @Autowired private BookRepo2 bookRepo2;

  @Autowired private BookService bookService;

  @Test
  void query2() {
//    log.info("{}", JsonUtil.toJson( bookRepo2.findBookIdAll()));
  }

  @Test
  void query() {
    List<Book> bookList = bookRepo2.findAllByNameLike("%飘%");
    if (bookList != null) {
      log.info("\n\n\n{}", bookList.size());
      bookList.forEach(b -> log.info("{}", JsonUtil.toJson(b)));
      log.info("\n\n\n");
    }
  }

  @Test
  void create() {
    //    纯色长袖t恤男士秋季韩版潮流纯棉衣服潮牌ins宽松百搭打底衫卫衣
    Book book =
        Book.create(new BookId(UUID.randomUUID().toString()), "凡客男士长短袖白色T恤纯色打底衫纯白纯黑圆领素色纯棉体恤");
    //    book.changeTags("book", "hhh");
    book.addAttr(1L, "1_属性1", Set.of(1L, 2L, 3L));
    book.addAttr(2L, "1_属性2", Set.of(3L));
    //
    //    book.change(new BookAd(true, false));
    //    book.change(List.of(new BookPrice(1, 100L), new BookPrice(2, 200L)));

    //    book.borrow(UserItem.create(1L));
    bookRepo2.save(book);
  }

  @Test
  void update() {
    bookRepo2
        .findByBookId(new BookId("82eba851-2d7a-4329-8956-296ff1090d26"))
        .ifPresent(
            o -> {
              o.changeAttr(2L, "test", Set.of(4L, 5L, 6L));
              bookRepo2.save(o);
            });
  }

  @Test
  void testError() {
    bookService.create();
  }
}
