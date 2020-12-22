package io.lpgph.auth.identity.user;

import io.lpgph.auth.common.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class UserController {

  @GetMapping("/")
  public Principal index(Principal principal) {
    return principal;
  }

  @GetMapping("/test")
  public Principal test(Principal principal) {
    return principal;
  }

  @GetMapping("/u1")
  public Principal u1(Principal principal) {
    log.info("\n\n u1  {}\n\n\n", JsonUtil.toJson(principal));
    return principal;
  }

  @GetMapping("/u2")
  public Object u2(Authentication authentication) {
    log.info("\n\n u2  {}\n\n", JsonUtil.toJson(authentication));
    return authentication;
  }

  @GetMapping("/u3")
  public Object u3() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return JsonUtil.toJson(authentication);
  }

  @GetMapping("/u4")
  public Object u4(@CurrentSecurityContext Object object) {
    return JsonUtil.toJson(object);
  }

  /** OAuth2AuthenticatedPrincipal 是由不透明令牌(Opaque) BearerTokenAuthentication 拥有 */
  @GetMapping("/u5")
  public String u5(@AuthenticationPrincipal Object token) {
    log.info("\n\n u5  {}\n\n", JsonUtil.toJson(token));
    return JsonUtil.toJson(token);
  }

  @GetMapping("/u6")
  public Object u6(@AuthenticationPrincipal(expression = "userId") Long userId) {
    return userId;
  }
}
