package io.lpgph.ddd.policy;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Table("policy_user")
public class PolicyUser {
  Long userId;
}
