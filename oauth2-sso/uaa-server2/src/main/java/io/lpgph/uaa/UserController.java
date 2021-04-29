package io.lpgph.uaa;

import io.lpgph.uaa.common.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@RestController
public class UserController {

  @GetMapping("/test")
  public String test() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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

  // 旧版
  @PreAuthorize("#oauth2.hasScope('all')")
  @GetMapping("/t1")
  public String tt() {
    return UUID.randomUUID().toString();
  }

  // need @EnableReactiveMethodSecurity
  // 需要有指定范围域 和 指定权限
  @PreAuthorize("hasAuthority('order/t1:read')")
  @GetMapping("/t2")
  public String t1() {
    return UUID.randomUUID().toString();
  }

  //  /** OAuth2AuthenticatedPrincipal 是由不透明令牌(Opaque) BearerTokenAuthentication 拥有 */
  //  @GetMapping("/u3")
  //  public String u3(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal token) {
  //    log.info("\n\n u3  {}\n\n", JsonUtil.toJson(token));
  //    return String.format("Hello, %s!", JsonUtil.toJson(token));
  //  }
  //
  //  // Jwt
  //  @GetMapping("/u4")
  //  public String u4(@AuthenticationPrincipal Jwt o) {
  //    log.info("\n\n u4  {}\n\n", JsonUtil.toJson(o));
  //    return String.format("Hello, %s!", JsonUtil.toJson(o));
  //  }
}
