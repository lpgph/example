package io.lpgph.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 2.4.0 版本以下 gateway 不支持 Token Relay 需要自定义
 * see https://github.com/jgrandja/oauth2login-gateway
 */
@Slf4j
@Component
public class TokenRelayGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

  private final ServerOAuth2AuthorizedClientRepository authorizedClientRepository;

  public TokenRelayGatewayFilterFactory(
      ServerOAuth2AuthorizedClientRepository authorizedClientRepository) {
    super(Object.class);
    this.authorizedClientRepository = authorizedClientRepository;
  }

  public GatewayFilter apply() {
    return apply((Object) null);
  }

  @Override
  public GatewayFilter apply(Object config) {
    return (exchange, chain) ->
        exchange
            .getPrincipal()
            .cast(OAuth2AuthenticationToken.class)
            .flatMap(authentication -> authorizedClient(exchange, authentication))
            .map(OAuth2AuthorizedClient::getAccessToken)
            .map(token -> withBearerAuth(exchange, token))
            .defaultIfEmpty(exchange)
            .flatMap(chain::filter);
  }

  private ServerWebExchange withBearerAuth(
      ServerWebExchange exchange, OAuth2AccessToken accessToken) {
    return exchange
        .mutate()
        .request(r -> r.headers(headers -> headers.setBearerAuth(accessToken.getTokenValue())))
        .build();
  }

  private Mono<OAuth2AuthorizedClient> authorizedClient(
      ServerWebExchange exchange, OAuth2AuthenticationToken oauth2Authentication) {
    return this.authorizedClientRepository.loadAuthorizedClient(
        oauth2Authentication.getAuthorizedClientRegistrationId(), oauth2Authentication, exchange);
  }
}
