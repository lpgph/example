package io.lpgph.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/** @see org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter */
@Slf4j
@RestController
public class OAuth2ClientController {
  private WebClient webClient = WebClient.builder().build();
  //  @Autowired private WebClient webClient;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private ReactiveClientRegistrationRepository reactiveClientRegistrationRepository;

  @GetMapping("/")
  public Object index(
      @RegisteredOAuth2AuthorizedClient("client1") OAuth2AuthorizedClient authorizedClient,
      @AuthenticationPrincipal OAuth2User oauth2User) {
    Map<String, Object> map = new HashMap<>();
    map.put("userName", oauth2User.getName());
    map.put("clientName", authorizedClient.getClientRegistration().getClientName());
    map.put("userInfo", oauth2User);
    return map;
  }

  /** 前端请求 authorization 授权认证后返回 token */
  @GetMapping("/authorization")
  public Object authorization(
      @RegisteredOAuth2AuthorizedClient("client1") OAuth2AuthorizedClient authorizedClient) {
    OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
    Map<String, Object> token = new HashMap<>();
    token.put("access_token", accessToken.getTokenValue());
    token.put("token_type", accessToken.getTokenType().getValue());
    if (authorizedClient.getRefreshToken() == null) {
      token.put("refresh_token", "");
    } else {
      token.put("refresh_token", authorizedClient.getRefreshToken().getTokenValue());
    }
    token.put(
        "expires_in",
        (accessToken.getExpiresAt() == null || accessToken.getIssuedAt() == null)
            ? null
            : accessToken
                .getExpiresAt()
                .minusMillis(accessToken.getIssuedAt().toEpochMilli())
                .getEpochSecond());
    token.put("scope", accessToken.getScopes());
    return token;
  }

  /** 前端页面绑定callback接口 认证后返回前端该接口 前端获取到code后 请求后端token接口 获取token */
  @GetMapping("/callback")
  public void callback(String code) throws JsonProcessingException {
    Mono<Map> body =
        webClient
            .get()
            .uri("http://localhost:8081/token?code={1}&appId={2}", code, "mobile")
            .exchange()
            .flatMap((rsp) -> rsp.bodyToMono(Map.class));
    body.subscribe(
        token -> {
          try {
            log.info("\n\n\ntoken\n{}\n\n\n", objectMapper.writeValueAsString(token));
            webClient
                .get()
                .uri("http://localhost:8081/order/role")
                .header("Authorization", "Bearer " + token.get("access_token"))
                .exchange()
                .flatMap((rsp) -> rsp.bodyToMono(String.class))
                .subscribe(rs -> log.info("\n\n\n{}\n\n\n", rs));
          } catch (JsonProcessingException e) {
            e.printStackTrace();
          }
        });
  }

  /** 前后端分离 认证code返回地址填写前端地址 前端拿到code后请求后端地址获取token */
  @GetMapping("/token")
  public Mono<Object> token(String code, String appId) throws JsonProcessingException {
    log.info("\n\n\n获取token code {}  appId {}\n\n\n", code, appId);
    Mono<ClientRegistration> reactiveRegistration =
        reactiveClientRegistrationRepository.findByRegistrationId(appId);
    ClientRegistration registration = reactiveRegistration.block(Duration.ofMillis(1));
    log.info("\n\n\n{}\n\n\n", objectMapper.writeValueAsString(registration));

    if (registration == null) throw new RuntimeException("appName 错误");
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("code", code);
    map.add("client_id", registration.getClientId());
    map.add("client_secret", registration.getClientSecret());
    map.add("grant_type", AuthorizationGrantType.AUTHORIZATION_CODE.getValue());
    map.add("redirect_uri", registration.getRedirectUriTemplate());
    log.info("\n\n\n{}\n\n\n", objectMapper.writeValueAsString(map));
    return webClient
        .post()
        .uri(registration.getProviderDetails().getTokenUri())
        .bodyValue(map)
        .exchange()
        .flatMap((rsp) -> rsp.bodyToMono(Object.class));
  }

  @GetMapping("/refresh")
  public Mono<Object> refresh(String refresh_token, String appId) {
    Mono<ClientRegistration> reactiveRegistration =
        reactiveClientRegistrationRepository.findByRegistrationId(appId);
    ClientRegistration registration = reactiveRegistration.block();
    if (registration == null) throw new RuntimeException("appName 错误");
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("grant_type", AuthorizationGrantType.REFRESH_TOKEN.getValue());
    map.add("refresh_token", refresh_token);
    return webClient
        .post()
        .uri(registration.getProviderDetails().getTokenUri(), map)
        .exchange()
        .flatMap((rsp) -> rsp.bodyToMono(Object.class));
  }
}
