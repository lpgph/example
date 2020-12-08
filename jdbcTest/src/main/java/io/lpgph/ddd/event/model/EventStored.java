package io.lpgph.ddd.event.model;

import io.lpgph.ddd.common.DomainEvent;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table("jdbc_event_stored")
public class EventStored {

  @Id private Long id;

  private String eventId;

  private Integer status;

  private String type;

  private DomainEvent eventBody;

  private LocalDateTime gmtSender;

  @CreatedDate private LocalDateTime gmtCreate;

  public EventStored(DomainEvent event) {
    this.eventId = event.getEventId();
    this.type = event.getClass().getName();
    this.eventBody = event;
    this.status = 0;
  }

  public void changeSuccess() {
    this.status = 1;
    this.gmtSender = LocalDateTime.now();
  }

  public void changeFailed() {
    this.status = 2;
    this.gmtSender = LocalDateTime.now();
  }
}
