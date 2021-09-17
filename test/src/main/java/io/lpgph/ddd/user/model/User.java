package io.lpgph.ddd.user.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Builder
@Getter
@Table("jdbc_user")
public class User {

  @Id private Long id;

  private String name;

  @MappedCollection(idColumn = "user_id")
  private Set<UserTag> tags;

  @Embedded.Empty private UserProp prop;

  @MappedCollection(idColumn = "user_id")
  private Set<UserAddress> addresses;

  public static User create(
      String name, Set<UserTag> tags, UserProp prop, Set<UserAddress> addresses) {
    //    user.registerEvent(new CreateUserEvent("user_____" + name));
    return User.builder().name(name).tags(tags).prop(prop).addresses(addresses).build();
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

  public void change(String name, Set<UserTag> tags, UserProp prop, Set<UserAddress> addresses) {
    this.name = name;
    this.tags = tags;
    this.prop = prop;
    this.addresses = addresses;
  }
}
