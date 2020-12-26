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
  void jsonTest() {
    String str = "[1,2,3,4,5,3,3,3,6,7,8,9,10,11,1,12]";
    Set<Integer> ary = JsonUtil.fromJson(str, new TypeReference<>() {});
    assert ary != null;
    ary.forEach(item -> log.info("{}", item));

    LinkedHashSet<Integer> ary2 = JsonUtil.fromJson(str, new TypeReference<>() {});
    assert ary2 != null;
    ary2.forEach(item -> log.info("{}", item));
  }

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
    //    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }

  @Test
  void update() {
    bookRepo
        .findById(10L)
        .ifPresent(
            o -> {
              o.changeAttr(new HashSet<>());
              bookRepo.save(o);
            });
  }

  @Test
  void create2() {
    Book book = new Book("<飘333>");
    //    book.changeTags("book", "hhh");
    BookAttr attr = new BookAttr(1L, "属性1");
    book.addAttr(attr);
    //
    //    book.change(new BookAd(true, false));
    //    book.change(List.of(new BookPrice(1, 100L), new BookPrice(2, 200L)));

    //    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }

  @Test
  void testError() {
    bookService.create();
  }

  @Test
  void create3() {
    Book book = new Book("<飘2222>");
    BookAttr attr = new BookAttr(1L, "属性1");
    //    attr.addValue(new BookAttrValue(1L, "value1"));
    book.addAttr(attr);
    //    book.borrow(UserItem.create(1L));
    bookRepo.save(book);
  }
}
