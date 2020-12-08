package io.lpgph.ddd.people.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
//@SQLDelete(sql = "update people set is_deleted=true where id=? and version=?")
//@Where(clause = "is_deleted=false")
@EntityListeners(AuditingEntityListener.class)
public class People {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //  @EmbeddedId private PeopleId id;

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

  @Column(name = "is_deleted", nullable = false)
  private Boolean deleted;

  public People(String name) {
    this.name = name;
    this.deleted = false;
  }
}
