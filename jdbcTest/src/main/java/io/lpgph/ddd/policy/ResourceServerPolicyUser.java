package io.lpgph.ddd.policy;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

/** 客户端授权策略 如对角色进行授权 对用户进行授权 对客户端进行授权 对组进行授权 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table("resource_server_policy")
public class ResourceServerPolicyUser {

  @Id private Long id;

  private Long clientId;

  private String name;

  private String description;

  /** 策略类型 基于角色 客户端 时间 用户 用户组 */
  private Integer type;

  /* 基于用户策略 */
  @MappedCollection(idColumn = "policy_id")
  private Set<PolicyUser> users;

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
  @Version private Long version;

  public static ResourceServerPolicyUser create(
      Long clientId, String name, String description, Set<PolicyUser> users) {
    return ResourceServerPolicyUser.builder()
        .clientId(clientId)
        .type(1)
        .name(name)
        .description(description)
        .users(users)
        .build();
  }
}
