package io.lpgph.ddd.people.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class People {

  @EmbeddedId private PeopleId id;

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

  public static People create(PeopleId peopleId, String name) {
    return People.builder().id(peopleId).name(name).build();
  }

  public void changeName(String newName) {
    this.name = newName;
  }
}
