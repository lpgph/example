package io.lpgph.auth.identity.user;

import io.lpgph.auth.common.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户资源服务器请求 <br>
 * 所有经过 ResourceSecurityConfig 配置的请求
 *
 * @see io.lpgph.auth.identity.security.config.ResourceSecurityConfig
 */
@Slf4j
@RestController
public class ResourceUserController {

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

  @GetMapping("/auth/info")
  public String u5(@AuthenticationPrincipal(expression = "claims") Map<String, Object> claims) {
    return JsonUtil.toJson(claims);
  }

  @GetMapping("/auth/user")
  public Object info(@AuthenticationPrincipal(expression = "claims[userId]") Long userId) {
    return userId;
  }
}
