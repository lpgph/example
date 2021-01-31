package io.lpgph.ddd.user.model;

import io.lpgph.ddd.common.StateEnum;
import lombok.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Setter
@Getter
public class User  {

  private UserId id;

  private String name;

  private StateEnum state;

  /** 创建时间 */
  private LocalDateTime gmtCreate;

  /** 创建人 */
  private Long createdBy;

  /** 最后修改时间 */
  private LocalDateTime gmtModified;

  /** 修改入 */
  private Long modifiedBy;

  private Long version;

  public User(String name) {
    this.name = name;
    this.state = StateEnum.ACTIVATED;
  }

}
