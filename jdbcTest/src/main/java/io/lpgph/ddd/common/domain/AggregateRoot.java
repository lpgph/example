package io.lpgph.ddd.common.domain;

import com.mysema.commons.lang.Assert;
import io.lpgph.ddd.common.DomainEvent;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/** @see org.springframework.data.domain.AbstractAggregateRoot */
@Setter(value = AccessLevel.PROTECTED)
@Getter(value = AccessLevel.PROTECTED)
public abstract class AggregateRoot extends Identified {

  /** 创建时间 */
  @CreatedDate private LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private Long modifiedBy;

  @Version private Long version;

  private final transient @Transient
  List<DomainEvent> domainEvents = new ArrayList<>();

  protected void registerEvent(DomainEvent event) {
    Assert.notNull(event, "Domain event must not be null!");
    this.domainEvents.add(event);
  }

  @DomainEvents
  protected Collection<DomainEvent> domainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    domainEvents.clear();
  }
}
