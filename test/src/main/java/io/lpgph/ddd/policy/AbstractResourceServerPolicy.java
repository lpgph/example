package io.lpgph.ddd.policy;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PROTECTED)
@Getter
@Table("resource_server_policy")
public abstract class AbstractResourceServerPolicy {

  @Id private Long id;

  private Long clientId;

  private String name;

  private String description;

  /** 策略类型 基于角色 客户端 时间 用户 用户组 */
  private Integer type;

  /** 创建时间 */
  @CreatedDate
  private LocalDateTime createdDate;

  /** 创建人 */
  @CreatedBy
  private Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate
  private LocalDateTime modifiedDate;

  /** 修改入 */
  @LastModifiedBy
  private Long modifiedBy;

  /** 乐观锁 */
  @Version
  private Long version;

}
