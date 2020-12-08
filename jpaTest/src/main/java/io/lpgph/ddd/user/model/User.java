package io.lpgph.ddd.user.model;

import io.lpgph.ddd.common.DomainEventPush;
import io.lpgph.ddd.common.StateEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Setter
@Getter
public class User implements DomainEventPush<UserEvent, UserId> {

  private UserId id;

  private String name;

  private StateEnum state;

  /** 创建时间 */
  private LocalDateTime gmtCreate;

  /** 创建人 */
  private Long createdBy;

  /** 最后修改时间 */
  private LocalDateTime gmtModified;

  /** 修改入 */
  private Long modifiedBy;

  private Long version;

  public User(String name) {
    this.name = name;
    this.state = StateEnum.ACTIVATED;
    this.domainEvents.add(new CreateUserEvent("user_____" + this.name));
  }

  private final transient List<UserEvent> domainEvents = new ArrayList<>();

  @Override
  public List<UserEvent> domainEvents(UserId userId) {
    domainEvents.forEach(userEvent -> userEvent.setRootId(userId));
    return domainEvents;
  }

  @Override
  public void callbackMethod() {
    domainEvents.clear();
  }
}
