package io.lpgph.ddd.policy;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Table("policy_role")
public class PolicyRole {
  Long roleId;
  @Column("is_required")
  Boolean required;
}
