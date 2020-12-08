package io.lpgph.ddd.event.model;

import io.lpgph.ddd.common.AbstractDomainEvent;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table(name = "event_stored")
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
@EntityListeners(AuditingEntityListener.class)
public class EventStored {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "event_id", length = 32, nullable = false)
  private String eventId;

  @ColumnDefault("0")
  @Column(name = "event_state", nullable = false)
  private Integer status;

  @Column(name = "type", length = 100, nullable = false)
  private String type;

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.CLASS,
      property = "type",
      include = JsonTypeInfo.As.EXISTING_PROPERTY)
  @Type(type = "json")
  @Column(name = "event_body", columnDefinition = "json")
  private AbstractDomainEvent eventBody;

  @Column(name = "gmt_sender")
  private LocalDateTime gmtSender;

  @CreatedDate
  @Column(name = "gmt_create", nullable = false)
  private LocalDateTime gmtCreate;

  public EventStored(AbstractDomainEvent event) {
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
