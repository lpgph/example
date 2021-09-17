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
@Getter
@Table("resource_server_policy")
public class ResourceServerPolicyUser extends AbstractResourceServerPolicy {

  /* 基于用户策略 */
  @MappedCollection(idColumn = "policy_id")
  private Set<PolicyUser> users;

  public static ResourceServerPolicyUser create(
      Long clientId, String name, String description, Set<PolicyUser> users) {
    ResourceServerPolicyUser obj = new ResourceServerPolicyUser(users);
    obj.setClientId(clientId);
    obj.setName(name);
    obj.setDescription(description);
    obj.setType(2);
    return obj;
  }
}
