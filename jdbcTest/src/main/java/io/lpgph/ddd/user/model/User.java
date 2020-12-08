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
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Setter
@Getter
@Table("jdbc_user")
public class User {

  @Id
  private UserId id;

  private String name;

  /** 创建时间 */
  @CreatedDate private LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private Long modifiedBy;

  @Version private Long version;

  public User(String name) {
    this.name = name;
    this.domainEvents.add(new CreateUserEvent("user_____" + this.name));
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
}
