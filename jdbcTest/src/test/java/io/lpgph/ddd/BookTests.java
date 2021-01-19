package io.lpgph.ddd;

import io.lpgph.ddd.book.BookService;
import io.lpgph.ddd.book.model.*;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@SpringBootTest
class BookTests {

  @Autowired private BookRepo bookRepo;

  @Autowired private BookService bookService;

  @Test
  void query() {
    //    List<Book> bookList = bookRepo.findAllByNameLike("%飘%");
    List<Book> bookList = bookRepo.listAllByName("男");
    if (bookList != null) {
      log.info("\n\n\n{}", bookList.size());
      bookList.forEach(b -> log.info("{}", JsonUtil.toJson(b)));
      log.info("\n\n\n");
    }
  }

  @Test
  void query2() {
    bookRepo.findById(28L).ifPresent(u -> log.info("\n\n\n{}\n\n\n", JsonUtil.toJson(u)));
  }

  @Test
  void create() {
    Random random = new Random();
    //    纯色长袖t恤男士秋季韩版潮流纯棉衣服潮牌ins宽松百搭打底衫卫衣
    Book book = Book.create("凡客男士长短袖白色T恤纯色打底衫纯白纯黑圆领素色纯棉体恤");
    //    book.changeTags("book", "hhh");
    book.addAttr(1L, "1_属性1", Set.of(1L, 2L, 3L));
    book.addAttr(2L, "1_属性2", Set.of(3L));

    book.changeInfo(
        new BookInfo(
            "test",
            Monetary.create(new BigDecimal("255.5")),
            Set.of(Tag.create("营销"), Tag.create("商业"))));
    //
    //    book.change(new BookAd(true, false));
    //    book.change(List.of(new BookPrice(1, 100L), new BookPrice(2, 200L)));

    //    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }

  @Test
  void remove2() {
    bookRepo
        .findById(40L)
        .ifPresent(
            u -> {
              u.remove();
              bookRepo.delete(u);
            });
  }

  @Test
  void remove() {
    bookRepo
        .findById(16L)
        .ifPresent(
            u -> {
              u.deactivated();
              bookRepo.save(u);
            });
  }

  @Test
  void update() {
    bookRepo
        .findById(10L)
        .ifPresent(
            o -> {
              o.changeAttr(2L, "test", Set.of(4L, 5L, 6L));
              bookRepo.save(o);
            });
  }

  @Test
  void testError() {
    bookService.create();
  }
}
