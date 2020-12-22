package io.lpgph.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  SecurityWebFilterChain configure(ServerHttpSecurity http) {
    return http.authorizeExchange(
            (exchanges) ->
                exchanges.pathMatchers("/login/**").permitAll().anyExchange().authenticated())
        .csrf()
        .disable()
//        .cors(spec -> spec.configurationSource(corsConfigurationSource()))
        .oauth2Login(withDefaults()) // 启用SSO支持 相当于@EnableOAuth2Sso
        //        .oauth2Login(
        //            login -> login.authenticationSuccessHandler(new
        // MyServerAuthenticationSuccessHandler()))
        // authenticationSuccessHandler 可修改认证成功后的跳转页面！
        .addFilterAt(new TokenFilter(), SecurityWebFiltersOrder.FIRST)
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
//    //    corsConfiguration.addExposedHeader("X-Auth-Token");
//    //    corsConfiguration.addExposedHeader("x-requested-with");
//    //    corsConfiguration.addExposedHeader("x-xsrf-token");
//    //    corsConfiguration.addExposedHeader("X-Session-Token");
//    //    corsConfiguration.addExposedHeader("X-**");
//    //    corsConfiguration.addExposedHeader("x-**");
//    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
//    configurationSource.registerCorsConfiguration("/**", corsConfiguration);
//    return configurationSource;
//  }
}
