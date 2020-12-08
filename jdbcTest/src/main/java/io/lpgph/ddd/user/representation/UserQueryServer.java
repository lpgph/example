package io.lpgph.ddd.user.representation;

import com.querydsl.core.types.Projections;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryServer {

  @Autowired
  private SQLQueryFactory queryFactory;

  public List<?> query() {
    return null;
//    QJdbcUser user = QJdbcUser.jdbcUser;
//    QJdbcUserBook userBook = QJdbcUserBook.jdbcUserBook;
//    QJdbcBook book = QJdbcBook.jdbcBook;
//    return queryFactory
//        .select(
//            Projections.bean(
//                UserBookResult.class,
//                user.id.as("userId"),
//                user.name.as("userName"),
//                book.id.as("bookId"),
//                book.name.as("bookName")))
//        .from(book)
//        .innerJoin(userBook)
//        .on(userBook.bookId.eq(book.id))
//        .innerJoin(user)
//        .on(userBook.userId.eq(user.id))
//        .fetch();
  }
}
