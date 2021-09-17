package io.lpgph.ddd.event.model;

import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.*;
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

  @Getter(AccessLevel.PRIVATE)
  private String eventBody;

  private LocalDateTime gmtSender;

  private LocalDateTime gmtCreate;

  public EventStored(DomainEvent event) {
    this.eventId = event.getEventId();
    this.type = event.getClass().getName();
    this.eventBody = JsonUtil.toJson(event);
    this.gmtCreate = event.getGmtCreate();
    this.status = 0;
  }

  public DomainEvent getEvent() throws ClassNotFoundException {
    if (this.eventBody == null || this.eventBody.isEmpty()) return null;
    return (DomainEvent) JsonUtil.fromJson(this.eventBody, Class.forName(type));
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
