package io.lpgph.uaa3.identity.user;

import io.lpgph.uaa3.common.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户资源服务器请求 <br>
 * 所有经过 ResourceSecurityConfig 配置的请求
 *
 * @see io.lpgph.uaa3.identity.security.config.ResourceSecurityConfig
 */
@Slf4j
@RestController
public class ResourceUserController {

  @GetMapping("/userinfo")
  public Object user(Principal principal) {
    log.info("\n\nuserinfo\n{}\n\n\n", JsonUtil.toJson(principal));
    Map<String, String> map = new HashMap<>();
    map.put("name", "admin");
    return map;
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
