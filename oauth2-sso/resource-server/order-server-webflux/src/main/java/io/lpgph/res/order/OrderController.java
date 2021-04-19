package io.lpgph.res.order;

import io.lpgph.res.order.bean.UserInfo;
import io.lpgph.res.order.json.JsonUtil;
import io.lpgph.res.order.rpc.IGoodsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class OrderController {

  private final IGoodsService goodsService;

  @GetMapping("/goods/show")
  public Object goods() {
    return goodsService.showMsg();
  }

  // 无需认证即可访问
  @GetMapping("/public/hello")
  public String hello() {
    return "This is public api." + UUID.randomUUID().toString();
  }

  // 认证后即可访问
  @GetMapping("/role")
  public String role() {
    return "order role " + UUID.randomUUID().toString();
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

  //  @GetMapping("/u2")
  //  public Object u2(JwtAuthenticationToken authentication) {
  //    log.info("\n\n u2  {}\n\n", JsonUtil.toJson(authentication));
  //    return authentication;
  //  }

  /** OAuth2AuthenticatedPrincipal 是由不透明令牌(Opaque) BearerTokenAuthentication 拥有 */
  @GetMapping("/u3")
  public String u3(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal token) {
    log.info("\n\n u3  {}\n\n", JsonUtil.toJson(token));
    return String.format("Hello, %s!", JsonUtil.toJson(token));
  }

  // Jwt
  @GetMapping("/u4")
  public String u4(@AuthenticationPrincipal Jwt o) {
    log.info("\n\n u4  {}\n\n", JsonUtil.toJson(o));
    return String.format("Hello, %s!", JsonUtil.toJson(o));
  }

  @GetMapping("/u5")
  public String u5(@AuthenticationPrincipal(expression = "claims") Object claims) {
    return JsonUtil.toJson(claims);
  }

  @GetMapping("/u10")
  public String u10(@AuthenticationPrincipal(expression = "claims") UserInfo claims) {

    log.info("\n\n u10  {}\n\n", JsonUtil.toJson(claims));
    return JsonUtil.toJson(claims);
  }

  @GetMapping("/u6")
  public Long u6(@AuthenticationPrincipal(expression = "claims[userId]") Long userId) {
    return userId;
  }

  @GetMapping("/u7")
  public Object u7() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return JsonUtil.toJson(authentication);
  }

  @GetMapping("/u8")
  public Object u8(@CurrentSecurityContext Object object) {
    return JsonUtil.toJson(object);
  }

  @GetMapping("/u9")
  public Mono<Authentication> u9() {
    Mono<Authentication> contextMono =
        ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication);
    return contextMono;
  }

  // 旧版
  @PreAuthorize("#oauth2.hasScope('all')")
  @GetMapping("/tt")
  public Mono<Object> tt() {
    return Mono.just("SCOPE_all " + UUID.randomUUID().toString());
  }

  // need @EnableReactiveMethodSecurity
  // 需要有指定范围域 和 指定权限
  @PreAuthorize("hasAuthority('SCOPE_all') and hasAuthority('order/t1:read')")
  @GetMapping("/t1")
  public Mono<Object> t1() {
    return Mono.just("SCOPE_all  order/t1:read " + UUID.randomUUID().toString());
  }

  // 需要有指定范围域 或 指定权限
  //  @PreAuthorize("hasAuthority('SCOPE_mall') or hasAuthority('order/t1:read')")
  @PreAuthorize("hasAnyAuthority('SCOPE_mall','order/t1:read')")
  @GetMapping("/t2")
  public Mono<Object> t2() {
    return Mono.just("SCOPE_mall  or order/t1:read " + UUID.randomUUID().toString());
  }

  @PreAuthorize("hasAuthority('order/t1:write')")
  @GetMapping("/t3")
  public Mono<Object> t3() {
    return Mono.just("order/t1:write " + UUID.randomUUID().toString());
  }

  // 在安全配置中 拥有范围域后 进行更详细的划分
  @PreAuthorize("hasAuthority('admin/t1:read')")
  @GetMapping("/admin/t1")
  public Mono<Object> adminT1() {
    return Mono.just("SCOPE_ADMIN  order/t1:read " + UUID.randomUUID().toString());
  }

  // 在安全配置中 拥有范围域后 直接可访问  此时访问者可以是未认证的
  @GetMapping("/admin/t2")
  public Mono<Object> adminT2() {
    return Mono.just("SCOPE_ADMIN   " + UUID.randomUUID().toString());
  }

  // 在该范围域内 必须用户必须认证后可访问
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/admin/t3")
  public Mono<Object> adminT3() {
    return Mono.just("SCOPE_ADMIN  isAuthenticated " + UUID.randomUUID().toString());
  }
}
