package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Joe Grandja
 * @since 0.0.1
 * @see
 *     org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
 */
// @EnableWebSecurity
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class ResourceServerConfig {

  // formatter:off
  //  @Bean
  //  SecurityFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
  //    http.authorizeRequests(
  //            (authorizeRequests) ->
  //                authorizeRequests
  //                    .antMatchers("/messages/**")
  //                    .access("hasAuthority('SCOPE_message.read')")
  //                    .antMatchers("/help/**")
  //                    .access("hasRole('USER')")
  //                    //                    .permitAll()
  //                    //                    .antMatchers(HttpMethod.POST)
  //                    //                    .access("#oauth2.hasScope('wirte')")
  //                    //                    .antMatchers(HttpMethod.GET)
  //                    //                    .access("#oauth2.hasScope('read')")
  //                    //                    .antMatchers("/**")
  //                    //                    .access("@permission.check(authentication,request)")
  //                    .anyRequest()
  //                    .authenticated())
  //        .oauth2ResourceServer(
  //            oauth2 ->
  //                oauth2.jwt(jwt ->
  // jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter())))
  //        .jwt();
  //    return http.build();
  //  }
  // formatter:on

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http.authorizeExchange(
            exchanges ->
                exchanges
                    .pathMatchers("/messages/**")
                    .hasAuthority("SCOPE_message.read")
                    .pathMatchers("/help/**")
                    .hasRole("USER")
                    .anyExchange()
                    .authenticated() // 其余接口认证后即可访问
            )
        .oauth2ResourceServer(
            oauth2 ->
                oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter())));
    return http.build();
  }

  /**
   * 手动提取权限属性 see <br>
   * https://docs.spring.io/spring-security/site/docs/current/reference/html5/#webflux-oauth2resourceserver-jwt-authorization
   * https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2resourceserver-jwt-authorization-extraction
   */
  public Converter<Jwt, Mono<AbstractAuthenticationToken>> getJwtAuthenticationConverter() {
    Map<String, String> authoritiesClaimName =
        new HashMap<>(GrantedAuthoritiesConverter.WELL_KNOWN_AUTHORITIES_CLAIM_NAMES);
    authoritiesClaimName.put("authorities", "");
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
        new GrantedAuthoritiesConverter(authoritiesClaimName));
    return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
  }
}
