package io.lpgph.ddd.people.representation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

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

  //  public List<?> query2() {
  //    QPeople people = QPeople.people;
  //    QPeopleId peopleId = QPeopleId.peopleId;
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
  //        .innerJoin(book.peoples, peopleId)
  //        .innerJoin(people)
  //        .on(people.id.eq(peopleId))
  //        .fetch();
  //  }
}
