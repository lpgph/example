package io.lpgph.ddd;

import com.querydsl.sql.codegen.MetaDataExporter;
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

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

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
  void query(){
    userRepository.findByName("test").ifPresent(u->    log.info("\n\n\n{}\n\n\n", JsonUtil.toJson(u)));
  }

  @Test
  void create() throws InterruptedException {
    User user = new User("test");
    userRepository.save(user);
    Thread.sleep(1000L);
    log.info("\n\n\n===============================\n\n\n");
  }

  @Test
  void createEvent() {
    eventStoredRepository.save(new EventStored(new CreateUserEvent(UserId.create(1L), "ttttt")));
  }
}
