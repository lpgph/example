package io.lpgph.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@EnableWebFluxSecurity
public class SecurityConfig {
  @Bean
  SecurityWebFilterChain httpSecurity(ServerHttpSecurity http) {
    // 排除用于服务间通信的后端 API
    // 保留客户端应用程序调用的公共 API
    return http.authorizeExchange(
            (exchanges) ->
                exchanges
                    .pathMatchers("**/public/**")
                    .permitAll()
                    .pathMatchers("**/admin/**") // 所有后台请求 拒绝访问
                    .denyAll()
                    .anyExchange()
                    .authenticated())
        .csrf()
        .disable()
        .cors(spec -> spec.configurationSource(corsConfigurationSource()))
        .oauth2Login(withDefaults())
        .build();
  }

  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    //    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    corsConfiguration.addAllowedOriginPattern("*");
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setMaxAge(3600L);
    corsConfiguration.addExposedHeader("access-control-allow-methods");
    corsConfiguration.addExposedHeader("access-control-allow-headers");
    corsConfiguration.addExposedHeader("access-control-allow-origin");
    corsConfiguration.addExposedHeader("access-control-max-age");
    corsConfiguration.addExposedHeader("X-Frame-Options");
    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
    configurationSource.registerCorsConfiguration("**", corsConfiguration);
    return configurationSource;
  }

  @Autowired private ObjectMapper objectMapper;

  @Bean
  public GrantedAuthoritiesMapper userAuthoritiesMapper() {
    return (authorities) -> {
      Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
      log.info("\n\n\n\n");
      authorities.forEach(
          authority -> {
            try {
              log.info("{}", objectMapper.writeValueAsString(authority));
            } catch (JsonProcessingException e) {
              e.printStackTrace();
            }
          });
      log.info("\n\n\n\n");
      mappedAuthorities.add(new SimpleGrantedAuthority("/test"));
      return mappedAuthorities;
    };
  }
}
