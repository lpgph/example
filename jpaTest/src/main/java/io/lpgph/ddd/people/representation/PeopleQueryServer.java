package io.lpgph.ddd.people.representation;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.lpgph.ddd.book.model.QBook;
import io.lpgph.ddd.people.model.QPeople;
import io.lpgph.ddd.people.model.QPeopleId;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PeopleQueryServer {

  private final JPAQueryFactory queryFactory;

  public PeopleQueryServer(EntityManager entityManager) {
    queryFactory = new JPAQueryFactory(entityManager);
  }

  //  @Cacheable("query")
  //  public List<?> query() {
  //    QPeople people = QPeople.people;
  //    QPeopleItem peopleItem = QPeopleItem.peopleItem;
  //    QBook book = QBook.book;
  //    return queryFactory
  //        .select(
  //            Projections.bean(
  //                PeopleBookResult.class,
  //                people.id.as("peopleId"),
  //                people.name.as("peopleName"),
  //                book.id.as("bookId"),
  //                book.name.as("bookName")))
  //        .from(book)
  //        .innerJoin(book.peoples, peopleItem)
  //        .innerJoin(people)
  //        .on(people.id.eq(peopleItem.peopleId))
  //        .fetch();
  //  }

  public List<?> query2() {
    QPeople people = QPeople.people;
    QPeopleId peopleId = QPeopleId.peopleId;
    QBook book = QBook.book;
    return queryFactory
        .select(
            Projections.bean(
                PeopleBookResult.class,
                people.id.as("peopleId"),
                people.name.as("peopleName"),
                book.id.as("bookId"),
                book.name.as("bookName")))
        .from(book)
        //          .innerJoin(book.peoples, peopleId)
        .innerJoin(people)
        .on(people.id.eq(peopleId))
        .orderBy(book.id.asc(), book.name.asc())
        .fetch();
  }
}
