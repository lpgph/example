package io.lpgph.ddd.user.persistence.jpa;

import io.lpgph.ddd.common.StateEnum;
import io.lpgph.ddd.user.model.CreateUserEvent;
import io.lpgph.ddd.user.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class JpaUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "user_state", nullable = false)
  private StateEnum state;

  /** 创建时间 */
  @CreatedDate private LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private Long modifiedBy;

  @Version private Long version;

  /** 是否已删除 0 否 1 是 */
  @ColumnDefault("false")
  @Column(name = "is_deleted", nullable = false)
  private Boolean deleted;

  public JpaUser(String name) {
    this.name = name;
    this.state = StateEnum.ACTIVATED;
  }

  public void delete() {
    this.deleted = true;
  }

  public void recovery() {
    this.deleted = false;
  }

  public static JpaUser create() {
    return null;
  }

  public void change(User user) {}

  public User convert() {
    return null;
  }
}
