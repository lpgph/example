package io.lpgph.ddd;

import com.querydsl.sql.codegen.MetaDataExporter;
import io.lpgph.ddd.event.model.EventStored;
import io.lpgph.ddd.user.UserApplicationService;
import io.lpgph.ddd.user.command.ChangeUserCommand;
import io.lpgph.ddd.user.command.CreateUserCommand;
import io.lpgph.ddd.user.model.*;
import io.lpgph.ddd.event.model.EventStoredRepository;
import io.lpgph.ddd.user.representation.UserQueryService;
import io.lpgph.ddd.user.representation.response.UserInfoResult;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
@SpringBootTest
class UserTests {

  @Autowired private EventStoredRepository eventStoredRepository;

  @Autowired private UserRepository userRepository;

  @Autowired private UserApplicationService userApplicationService;

  @Autowired private UserQueryService userQueryService;

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
    Random random = new Random(System.currentTimeMillis());
    User user =
        User.create(
            "test",
            Set.of(UserTag.create("阳光"), UserTag.create("运动"), UserTag.create("勇敢")),
            new UserProp(true, 3),
            Set.of(new UserAddress("家", "连云路"), new UserAddress("公司", "平安路")));
    userRepository.save(user);
    log.info("\n\n\n===============================\n\n\n");
  }

  @Test
  void query2() {
    userRepository.findById(38L).ifPresent(u -> log.info("\n\n\n{}\n\n\n", JsonUtil.toJson(u)));
  }

  @Test
  void query3() {
    List<User> list = userRepository.findAllByProp(new UserProp(true, 4));
    log.info("\n\n\n{}\n\n\n", JsonUtil.toJson(list));
  }

  @Test
  void remove() {
    userRepository.findById(38L).ifPresent(u -> userRepository.remove(u));
  }

  @Test
  void change() {
    userRepository
        .findById(38L)
        .ifPresent(
            o -> {
              o.change(
                  "李四",
                  Set.of(UserTag.create("阳光"), UserTag.create("运动"), UserTag.create("勇敢")),
                  new UserProp(true, 3),
                  Set.of(new UserAddress("家", "连云路"), new UserAddress("公司", "平安路")));
              userRepository.save(o);
            });
  }

  @Test
  void serviceCreate() {
    CreateUserCommand command = new CreateUserCommand("test2", true, 3, Set.of("阳光", "运动", "勇敢"));
    userApplicationService.create(command);
  }

  @Test
  void serviceChange() {
    ChangeUserCommand command = new ChangeUserCommand("李四2", true, 3, Set.of("阳光", "运动", "勇敢"));
    userApplicationService.change(2L, command);
  }

  @Test
  void serviceQuery() {
    UserInfoResult result = userQueryService.getInfoById(2L);
    log.info(JsonUtil.toJson(result));
  }

  @Test
  void createEvent() {
    eventStoredRepository.save(new EventStored(new CreateUserEvent(1L, "ttttt")));
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
