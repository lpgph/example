package io.lpgph.gateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

/** @see org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter */
@Slf4j
@RestController
@AllArgsConstructor
public class OAuth2ClientController {

  private final WebClient webClient;

  private final ReactiveClientRegistrationRepository reactiveClientRegistrationRepository;



  @GetMapping("/test")
  public String test() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }



  @GetMapping("/")
  public Object index(
      @RegisteredOAuth2AuthorizedClient("cms") OAuth2AuthorizedClient authorizedClient,
      @AuthenticationPrincipal OAuth2User oauth2User) {
    Map<String, Object> map = new HashMap<>();
    map.put("userName", oauth2User.getName());
    map.put("clientName", authorizedClient.getClientRegistration().getClientName());
    map.put("userInfo", oauth2User);
    return map;
  }

  /*  1. 请求网关 网关需要授权  转向登录客户端  登录客户端进行(帐号密码/第三方授权)登录 认证成功后跳转到授权服务  授权服务生成token
   *
   *
   *
   *
   */
  @PostMapping("/login")
  public Object login(@RequestBody LoginInfo loginInfo) {
    Mono<ClientRegistration> reactiveRegistration =
        reactiveClientRegistrationRepository.findByRegistrationId("app");
    ClientRegistration registration = reactiveRegistration.block();
    if (registration == null) throw new RuntimeException("appName 错误");
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("client_id", registration.getClientId());
    map.add("client_secret", registration.getClientSecret());
    map.add("grant_type", "password");
    map.add("username", loginInfo.getUsername());
    map.add("password", loginInfo.getPassword());
    return this.webClient
        .post()
        .uri(registration.getProviderDetails().getTokenUri())
        .attributes(clientRegistrationId("app"))
        .bodyValue(map)
        .retrieve()
        .bodyToMono(Object.class)
        .block();
  }
}
