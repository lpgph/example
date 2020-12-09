package io.lpgph.ddd;

import com.querydsl.sql.codegen.MetaDataExporter;
import io.lpgph.ddd.book.model.Book;
import io.lpgph.ddd.book.model.BookRepo;
import io.lpgph.ddd.event.model.EventStored;
import io.lpgph.ddd.user.model.CreateUserEvent;
import io.lpgph.ddd.user.model.UserId;
import io.lpgph.ddd.user.model.UserRepository;
import io.lpgph.ddd.user.model.User;
import io.lpgph.ddd.event.model.EventStoredRepository;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class UserTests {

  @Autowired private EventStoredRepository eventStoredRepository;

  @Autowired private UserRepository userRepository;

  @Autowired
  private BookRepo bookRepo;
//
//  @Autowired private DataSource dataSource;
//
//  // 生成文件
//  @Test
//  void export() throws SQLException {
//    java.sql.Connection conn = dataSource.getConnection();
//    MetaDataExporter exporter = new MetaDataExporter();
//    exporter.setPackageName("io.lpgph.ddd.query");
//    exporter.setTargetFolder(new File("src/main/java"));
//    exporter.export(conn.getMetaData());
//  }

  @Autowired
  DatabaseClient database;

  @Test
  void createBook(){
    bookRepo.save(new Book("秋"))
            .as(StepVerifier::create)
            .expectNextCount(1)
            .verifyComplete();
  }

  @Test
  void create() {
    User user = new User("test");
    userRepository.save(user)
            .as(StepVerifier::create)
            .expectNextCount(1)
            .verifyComplete();
  }

  @Test
  void query() {
    userRepository.findByName("test")  .as(StepVerifier::create)
            .assertNext(ac->{
              log.info("\n\n\n{}\n\n\n", JsonUtil.toJson(ac));
    })
//            .expectNextCount(1)
            .verifyComplete();

  }

  @Test
  void createEvent() {
    eventStoredRepository.save(new EventStored(new CreateUserEvent(UserId.create(1L), "ttttt")));
  }
}
