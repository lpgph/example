package io.lpgph.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.web.server.context.SecurityContextServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/** @see org.springframework.security.web.context.SecurityContextPersistenceFilter */
@Slf4j
public class TokenFilter implements WebFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    try {
      log.info(
          "\n\nrequest_uri  {}\nheaders\n  {}\ncookies\n  {}\n\n",
          request.getURI(),
          objectMapper.writeValueAsString(request.getHeaders()),
          objectMapper.writeValueAsString(request.getCookies()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return chain.filter(exchange);
  }
}
