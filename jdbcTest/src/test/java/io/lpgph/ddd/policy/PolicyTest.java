package io.lpgph.ddd.policy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@Slf4j
@SpringBootTest
public class PolicyTest {

  @Autowired private ResourceServerPolicyUserRepository resourceServerPolicyUserRepository;

  @Autowired private ResourceServerPolicyRoleRepository resourceServerPolicyRoleRepository;

  @Test
  public void userTest() {
    ResourceServerPolicyUser userPolicy =
        ResourceServerPolicyUser.create(1L, "用户策略", "用户策略", Set.of(new PolicyUser(1L)));
    resourceServerPolicyUserRepository.save(userPolicy);
  }

  @Test
  public void roleTest() {
    ResourceServerPolicyRole rolePolicy =
        ResourceServerPolicyRole.create(6L, "角色策略6", "角色策略", Set.of(new PolicyRole(1L, false)));
    resourceServerPolicyRoleRepository.save(rolePolicy);
  }
}
