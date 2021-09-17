package io.lpgph.ddd.common.domain;

import io.lpgph.ddd.common.StateEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 聚合对象 基类 TODO 做为基类使用无效 无法注入
 *
 * @see org.springframework.data.domain.AbstractAggregateRoot
 */
@Setter
@Getter
public abstract class AbstractAggregateRoot {

//  /* 启用状态 */
//  private StateEnum state;
//
//  /** 创建时间 */
//  @CreatedDate private LocalDateTime gmtCreate;
//
//  /** 创建人 */
//  @CreatedBy private Long createdBy;
//
//  /** 最后修改时间 */
//  @LastModifiedDate private LocalDateTime gmtModified;
//
//  /** 修改入 */
//  @LastModifiedBy private Long modifiedBy;
//
//  /** 乐观锁 */
//  @Version private Long version;
//
//  private final transient @Transient List<DomainEvent> domainEvents = new ArrayList<>();
//
//  protected void registerEvent(DomainEvent event) {
//    Assert.notNull(event, "Domain event must not be null!");
//    this.domainEvents.add(event);
//  }
//
//  @DomainEvents
//  protected Collection<DomainEvent> domainEvents() {
//    return Collections.unmodifiableList(domainEvents);
//  }
//
//  @AfterDomainEventPublication
//  protected void clearDomainEvents() {
//    domainEvents.clear();
//  }
}
