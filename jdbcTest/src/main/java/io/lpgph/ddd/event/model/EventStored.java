package io.lpgph.ddd.event.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

  @JsonTypeInfo(
          use = JsonTypeInfo.Id.CLASS,
          property = "type",
          include = JsonTypeInfo.As.EXISTING_PROPERTY)
  private DomainEvent eventBody;

  private LocalDateTime gmtSender;

  private LocalDateTime gmtCreate;

  public EventStored(DomainEvent event) {
    this.eventId = event.getEventId();
    this.type = event.getClass().getName();
    this.eventBody = event;
    this.gmtCreate = event.getGmtCreate();
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
