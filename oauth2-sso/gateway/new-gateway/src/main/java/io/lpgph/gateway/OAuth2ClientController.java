package io.lpgph.gateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

/** @see org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter */
@Slf4j
@RestController
@AllArgsConstructor
public class OAuth2ClientController {

  private final WebClient webClient;

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

//  @PostMapping("/login")
//  public Object login(String username) {
//    return this.webClient
//            .post().attributes("username","")
//            .uri("http://127.0.0.1:8090/oauth/token")
//            .attributes(clientRegistrationId("app"))
//            .retrieve()
//            .bodyToMono(Object.class)
//            .block();
//  }
}
