package io.lpgph.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
public class DefaultController {

  private final ObjectMapper objectMapper;

  @GetMapping("/api")
  public Object auth(Authentication authentication) throws Exception {
    log.info("\nAuthentication {}\n", objectMapper.writeValueAsString(authentication));
    return authentication;
  }

  @GetMapping("/")
  public Object index(
      @RegisteredOAuth2AuthorizedClient("c2") OAuth2AuthorizedClient authorizedClient,
      @AuthenticationPrincipal OAuth2User oauth2User) {
    Map<String, Object> map = new HashMap<>();
    map.put("userName", oauth2User.getName());
    map.put("clientName", authorizedClient.getClientRegistration().getClientName());
    map.put("userInfo", oauth2User);
    return map;
  }

}
