package io.lpgph.ddd;

import io.lpgph.ddd.event.model.EventStoredDao;
import io.lpgph.ddd.user.model.User;
import io.lpgph.ddd.user.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserTests {
  @Autowired private UserRepository userRepo;

  @Autowired private EventStoredDao eventStoredDao;

  @Test
  void create() throws InterruptedException {
    User user = new User("test");
    userRepo.save(user);
    Thread.sleep(1000L);
    log.info("\n\n\n===============================\n\n\n");
  }

  //
  //  @Test
  //  void createEvent() {
  //    eventStoredRepo.save(new EventStored(new CreateUserEvent(1L, "ttttt")));
  //  }

  //  @Test
  //  void update() {
  //    userRepo
  //        .findById(1L)
  //        .ifPresent(
  //            user -> {
  //              user.setName("test2222");
  //              userRepo.save(user);
  //            });
  //  }
  //
  //  @Test
  //  void change() {
  //    User user = new User();
  //    user.setId(1L);
  //    user.setName("aaaaaaa");
  //    userRepo.save(user);
  //  }
}
