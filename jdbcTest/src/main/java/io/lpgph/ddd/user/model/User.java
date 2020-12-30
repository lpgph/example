package io.lpgph.ddd.user.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@Getter
@Table("jdbc_user")
public class User {

  @Id private UserId id;

  private String name;

  private String[] tags;

  private UserProp prop;

  private UserAddress[] address;

  /** 创建时间 */
  @CreatedDate private final LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private final Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private final LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private final Long modifiedBy;

  @Version private final Long version;

  public static User create(String name) {
    User user = User.builder().name(name).build();
    user.domainEvents.add(new CreateUserEvent("user_____" + name));
    return user;
  }

  @Transient private final transient List<UserEvent> domainEvents = new ArrayList<>();

  @DomainEvents
  private List<UserEvent> domainEvents() {
    log.info("\n\n\n执行领域事件\n\n\n");
    domainEvents.forEach(userEvent -> userEvent.setUserId(this.id));
    return domainEvents;
  }

  @AfterDomainEventPublication
  private void callbackMethod() {
    domainEvents.clear();
  }

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
