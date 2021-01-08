package io.lpgph.ddd.book;

import io.lpgph.ddd.book.model.*;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.Set;

@Slf4j
@Service
public class BookService {

  @Autowired private BookRepo bookRepo;

  @Transactional
  public void create() {
    Random random = new Random(System.currentTimeMillis());
    Book book = Book.create("<飘333>");
    Book newBook = bookRepo.save(book);
    log.info("\n\nbook\n{}\nnewBook\n{}\n\n", JsonUtil.toJson(book), JsonUtil.toJson(newBook));

    newBook.addAttr(1L, "属性1", Set.of(1L, 2L, 3L));

    //    newBook.change(new BookAd(true, false));
    if (true) throw new RuntimeException("ttttt");
    //    book.change(List.of(new BookPrice(1, 100L), new BookPrice(2, 200L)));

    //    newBook.borrow(UserItem.create(1L));
    bookRepo.save(newBook);
  }
}
