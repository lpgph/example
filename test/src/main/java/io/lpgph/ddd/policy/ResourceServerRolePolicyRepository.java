package io.lpgph.ddd.policy;

import org.springframework.stereotype.Repository;

// @Repository
// public interface ResourceServerPolicyRoleRepository<T extends AbstractResourceServerPolicy>
//    extends CrudRepository<T, Long> {}

@Repository
public interface ResourceServerRolePolicyRepository
    extends AbstractResourceServerPolicyRepository<ResourceServerPolicyRole> {}
