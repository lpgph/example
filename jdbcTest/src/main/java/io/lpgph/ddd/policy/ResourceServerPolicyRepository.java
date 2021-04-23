package io.lpgph.ddd.policy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// @Repository
// public interface ResourceServerPolicyRoleRepository<T extends AbstractResourceServerPolicy>
//    extends CrudRepository<T, Long> {}

@Repository
public interface ResourceServerPolicyRepository
    extends CrudRepository<AbstractResourceServerPolicy, Long> {}
