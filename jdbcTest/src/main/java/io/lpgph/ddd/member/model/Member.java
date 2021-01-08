package io.lpgph.ddd.member.model;

import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.user.model.UserAddress;
import io.lpgph.ddd.user.model.UserProp;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@Builder
@Getter
public class Member {

  private MemberId id;

  private String name;

  private String[] tags;

  private UserProp prop;

  private UserAddress[] address;

  /** 创建时间 */
  private LocalDateTime gmtCreate;

  /** 创建人 */
  private Long createdBy;

  /** 最后修改时间 */
  private LocalDateTime gmtModified;

  /** 修改入 */
  private Long modifiedBy;

  private Long version;

  private final transient List<DomainEvent> domainEvents = new ArrayList<>();

  protected void registerEvent(DomainEvent event) {
    Assert.notNull(event, "Domain event must not be null!");
    this.domainEvents.add(event);
  }

  public Collection<DomainEvent> domainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  public void clearDomainEvents() {
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
