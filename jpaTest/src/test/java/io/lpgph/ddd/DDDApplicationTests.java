package io.lpgph.ddd;

import io.lpgph.ddd.book.model.Book;
import io.lpgph.ddd.book.model.BookRepo;
import io.lpgph.ddd.people.model.People;
import io.lpgph.ddd.people.model.PeopleRepo;
import io.lpgph.ddd.people.representation.PeopleQueryServer;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@SpringBootTest
class DDDApplicationTests {
  @Autowired private BookRepo bookRepo;
  @Autowired private PeopleRepo peopleRepo;

  @Autowired private PeopleQueryServer peopleQueryServer;

  private String getLong(long money, long divisor, int scale) {
    return new BigDecimal(money)
        .divide(BigDecimal.valueOf(divisor), scale, RoundingMode.DOWN)
        .toString();
  }

  private long getLong(BigDecimal money, long multiplicand) {
    return money.multiply(BigDecimal.valueOf(multiplicand)).longValue();
  }

  @Test
  void showMoney() {
    log.info("{}", getLong(new BigDecimal(1231399999L), 100));
    log.info(getLong(231231222, 100, 2));
  }

  @Test
  void create() {
//    People people = new People("张三");
//    People savePeople = peopleRepo.save(people);
//    Book book = new Book("<围城>");
//    book.borrow(savePeople.getId());
//    bookRepo.save(book);
  }

  @Test
  void queryTest() {
//    log.info(JsonUtil.toJson(peopleQueryServer.query()));
  }
}
