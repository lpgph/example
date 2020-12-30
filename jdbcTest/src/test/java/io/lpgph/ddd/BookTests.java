package io.lpgph.ddd;

import io.lpgph.ddd.book.BookService;
import io.lpgph.ddd.book.model.*;
import io.lpgph.ddd.utils.json.JsonUtil;
import io.lpgph.ddd.utils.json.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class BookTests {

  @Autowired private BookRepo bookRepo;

  @Autowired private BookService bookService;

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
    //    纯色长袖t恤男士秋季韩版潮流纯棉衣服潮牌ins宽松百搭打底衫卫衣
    Book book = Book.create("凡客男士长短袖白色T恤纯色打底衫纯白纯黑圆领素色纯棉体恤");
    //    book.changeTags("book", "hhh");
    book.addAttr(1L, "1_属性1", Set.of(1L, 2L, 3L));
    book.addAttr(2L, "1_属性2", Set.of(3L));
    //
    //    book.change(new BookAd(true, false));
    //    book.change(List.of(new BookPrice(1, 100L), new BookPrice(2, 200L)));

    //    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }

  @Test
  void update() {
    bookRepo
        .findById(1L)
        .ifPresent(
            o -> {
              o.changeAttr(1L, 3L, "test", Set.of(4L, 5L, 6L));
              bookRepo.save(o);
            });
  }

  @Test
  void testError() {
    bookService.create();
  }
}
