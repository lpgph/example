package io.lpgph.client;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
public class SecurityConfig {

  //  public void configure(WebSecurity web) {
  //    web
  //            .ignoring()
  //            .antMatchers("/webjars/**");
  //
  //  }

  @Order(Ordered.HIGHEST_PRECEDENCE)
  @Bean
  SecurityWebFilterChain configure(ServerHttpSecurity http) {
    return http.securityMatcher(new PathPatternParserServerWebExchangeMatcher("/api/**"))
        .authorizeExchange((exchanges) -> exchanges.anyExchange().authenticated())
        .csrf()
        .disable()
//        .cors(spec -> spec.configurationSource(corsConfigurationSource()))
        .build();
  }

  /** oauth2认证 */
  @Bean
  SecurityWebFilterChain oauthConfigure(ServerHttpSecurity http) {
    return http.authorizeExchange(
            (exchanges) ->
                exchanges
                    //                    .pathMatchers("/webjars/**")
                    //                    .permitAll()
                    //                    .pathMatchers("/login")
                    //                    .permitAll()
                    .anyExchange()
                    .authenticated())
        .csrf()
        .disable()
//        .cors(spec -> spec.configurationSource(corsConfigurationSource()))
        //        .formLogin(form -> form.loginPage("/login"))
        .oauth2Login(withDefaults()) // 启用SSO支持 相当于@EnableOAuth2Sso
//        .oauth2Client(withDefaults())
        .build();
  }

//  @Bean
//  public CorsConfigurationSource corsConfigurationSource() {
//    CorsConfiguration corsConfiguration = new CorsConfiguration();
//    corsConfiguration.addAllowedOrigin("*");
//    corsConfiguration.addAllowedHeader("*");
//    corsConfiguration.addAllowedMethod("*");
//    corsConfiguration.setAllowCredentials(true);
//    corsConfiguration.setMaxAge(3600L);
//    corsConfiguration.addExposedHeader("access-control-allow-methods");
//    corsConfiguration.addExposedHeader("access-control-allow-headers");
//    corsConfiguration.addExposedHeader("access-control-allow-origin");
//    corsConfiguration.addExposedHeader("access-control-max-age");
//    corsConfiguration.addExposedHeader("X-Frame-Options");
//    corsConfiguration.addExposedHeader("x-auth-token");
//    corsConfiguration.addExposedHeader("x-requested-with");
//    corsConfiguration.addExposedHeader("x-xsrf-token");
//    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
//    configurationSource.registerCorsConfiguration("/**", corsConfiguration);
//    return configurationSource;
//  }
}
