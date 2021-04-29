package io.lpgph.uaa.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@AllArgsConstructor
@Configuration
@Service
public class UserServer {

  private final WebClient webClient;

  //  @Value("${resource-uri}")
  //  String resourceUri;

  public UserInfo getUserByUsername(String username) {
    return this.webClient
        .get()
        .uri("http://127.0.0.1:8071/user/auth")
        .attributes(clientRegistrationId("uaa-client"))
        .retrieve()
        .bodyToMono(UserInfo.class)
        .block();
  }
}
