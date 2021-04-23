package io.lpgph.ddd.policy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceServerPolicyUserRepository extends CrudRepository<ResourceServerPolicyUser, Long> {


}
