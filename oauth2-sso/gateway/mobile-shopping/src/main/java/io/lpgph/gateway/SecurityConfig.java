package io.lpgph.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
public class SecurityConfig {

  //
  // http://localhost:8090/oauth/authorize?response_type=code&client_id=mobile&scope=all&state=uYY300XjoXDkRiHAriQdLIUZ5sNrKaX9pYzSsWJzJZg%3D&redirect_uri=http://localhost:8081/callback
  @Bean
  public SecurityWebFilterChain httpSecurity(ServerHttpSecurity http) {
    return http.authorizeExchange(exchange -> exchange.anyExchange().permitAll())
        .csrf()
        .disable()
        .oauth2Login(withDefaults())
        //        .oauth2Client(withDefaults())
        .build();
  }
}
