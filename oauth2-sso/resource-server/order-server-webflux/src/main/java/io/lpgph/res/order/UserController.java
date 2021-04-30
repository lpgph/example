package io.lpgph.res.order;

import io.lpgph.res.order.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController {

  private final PasswordEncoder passwordEncoder;

  @GetMapping("/userinfo")
  public Object user(@AuthenticationPrincipal(expression = "claims") Map<String, Object> claims) {
    log.info("\n\nuserinfo\n{}\n\n\n", JsonUtil.toJson(claims));
    Map<String, Object> attributes = new HashMap<>();
    attributes.put("sub", claims.get("sub"));
    attributes.put("userId", claims.get("userId"));
    attributes.put("exp", claims.get("exp"));
    attributes.put("iat", claims.get("iat"));
    attributes.put("authorities", claims.get("authorities"));
    attributes.put("jti", claims.get("jti"));
    attributes.put("scope", claims.get("scope"));
    return attributes;
  }

  @GetMapping("/user/auth")
  public UserInfo userInfo(String username) {
    log.info("\n\n  获取用户信息  {} \n\n", username);
    return new UserInfo(
        Math.abs(new Random().nextLong()),
        "admin",
        passwordEncoder.encode("admin"),
        Set.of("ROLE_ADMIN", "order/t1:read", "order/t1:write"));
  }
}
