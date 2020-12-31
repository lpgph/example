package io.lpgph.ddd;

import com.querydsl.sql.codegen.MetaDataExporter;
import io.lpgph.ddd.event.model.EventStored;
import io.lpgph.ddd.user.model.*;
import io.lpgph.ddd.event.model.EventStoredRepository;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class UserTests {

  @Autowired private EventStoredRepository eventStoredRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private DataSource dataSource;

  // 生成文件
  @Test
  void export() throws SQLException {
    java.sql.Connection conn = dataSource.getConnection();
    MetaDataExporter exporter = new MetaDataExporter();
    exporter.setPackageName("io.lpgph.ddd.query");
    exporter.setTargetFolder(new File("src/main/java"));
    exporter.export(conn.getMetaData());
  }

  @Test
  void query() {
    userRepository
        .findByName("test")
        .ifPresent(u -> log.info("\n\n\n{}\n\n\n", JsonUtil.toJson(u)));
  }

  @Test
  void create() throws InterruptedException {
    User user = User.create("test");
    user.changeTags("阳光", "运动", "勇敢");
    user.changeProp(new UserProp(true, 3));
    user.changeAddress(new UserAddress("家", "连云路"), new UserAddress("公司", "平安路"));
    userRepository.save(user);
    log.info("\n\n\n===============================\n\n\n");
  }

  @Test
  void change() {
    userRepository
        .findById(UserId.create(14L))
        .ifPresent(
            o -> {
              o.changeName("李四");
              userRepository.save(o);
            });
  }

  @Test
  void createEvent() {
    eventStoredRepository.save(new EventStored(new CreateUserEvent(UserId.create(1L), "ttttt")));
  }

  @Test
  void queryEvent() {
    eventStoredRepository
        .findAllByStatusIn(Set.of(0, 2))
        .forEach(
            item -> {
              try {
                log.info("\n{}\n", JsonUtil.toJson(item.getEvent()));
              } catch (ClassNotFoundException e) {
                e.printStackTrace();
              }
            });
  }
}
