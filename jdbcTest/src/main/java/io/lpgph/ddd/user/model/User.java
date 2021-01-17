package io.lpgph.ddd.user.model;

import io.lpgph.ddd.common.DomainEvent;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@Getter
@Table("jdbc_user")
public class User{

  @Embedded(prefix = "user_", onEmpty = Embedded.OnEmpty.USE_NULL)
  private UserId userId;

  private String name;

  private String[] tags;

  private UserProp prop;

  private UserAddress[] address;

  public static User create(UserId userId, String name) {
    User user = User.builder().userId(userId).name(name).build();
    //    user.registerEvent(new CreateUserEvent("user_____" + name));
    return user;
  }
  //
  //  @Transient private final transient List<DomainEvent> domainEvents = new ArrayList<>();
  //
  //  @DomainEvents
  //  private List<DomainEvent> domainEvents() {
  //    log.info("\n\n\n执行领域事件\n\n\n");
  //    domainEvents.forEach(
  //        event -> {
  //          if (event instanceof UserEvent) {
  //            ((UserEvent) event).setUserId(this.id);
  //          }
  //        });
  //    return domainEvents;
  //  }
  //
  //  @AfterDomainEventPublication
  //  private void callbackMethod() {
  //    domainEvents.clear();
  //  }

  public void changeTags(String... tag) {
    this.tags = tag;
  }

  public void changeAddress(UserAddress... addresses) {
    this.address = addresses;
  }

  public void changeProp(UserProp prop) {
    this.prop = prop;
  }

  public void changeName(String name) {
    this.name = name;
  }
}
