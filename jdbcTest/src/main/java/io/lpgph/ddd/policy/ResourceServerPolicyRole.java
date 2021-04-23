package io.lpgph.ddd.policy;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

/** 客户端授权策略 如对角色进行授权 对用户进行授权 对客户端进行授权 对组进行授权 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class ResourceServerPolicyRole extends AbstractResourceServerPolicy {


//  @Embedded.Empty private ResourceServerPolicy resourceServerPolicy;

  /* 基于角色策略 */
  @MappedCollection(idColumn = "policy_id")
  private Set<PolicyRole> roles;


//  /** 创建时间 */
//  @CreatedDate
//  private LocalDateTime createdDate;
//
//  /** 创建人 */
//  @CreatedBy
//  private Long createdBy;
//
//  /** 最后修改时间 */
//  @LastModifiedDate
//  private LocalDateTime modifiedDate;
//
//  /** 修改入 */
//  @LastModifiedBy
//  private Long modifiedBy;
//
//  /** 乐观锁 */
//  @Version
//  private Long version;

//  @Embedded.Empty private Auditor auditor;

  public static ResourceServerPolicyRole create(
      Long clientId, String name, String description, Set<PolicyRole> roles) {
//    return ResourceServerPolicyRole.builder()
//        .resourceServerPolicy(new ResourceServerPolicy(clientId, name, description, 1))
//        .roles(roles)
//        .build();

    ResourceServerPolicyRole obj =   ResourceServerPolicyRole.builder()
        .roles(roles)
        .build();
    obj.setClientId(clientId);
    obj.setName(name);
    obj.setDescription(description);
    obj.setType(1);
    return obj;
  }

  //  public static ResourceServerPolicyRole create(
  //      Long clientId, String name, String description, Set<PolicyRole> roles) {
  //    return ResourceServerPolicyRole.builder()
  //        .clientId(clientId)
  //        .type(1)
  //        .name(name)
  //        .description(description)
  //        .roles(roles)
  //        .build();
  //  }
}
