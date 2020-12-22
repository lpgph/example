package io.lpgph.res.order.config;

import io.lpgph.res.order.security.GrantedAuthoritiesConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /** 注意 根据路径匹配 如果路径没有认证改通过 则会直接返回认证失败 */
  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http.authorizeExchange(
            exchanges ->
                exchanges
                    .pathMatchers("/public/**")
                    .permitAll() // 无需认证即可访问
                    .pathMatchers("/rpc/**")
                    .hasAuthority("SCOPE_RPC") // 访问该请求需要有范围域
                    // 权限细度 客户端的权限范围域 -> 用户所拥有的角色和资源 划分范围域后可在接口上注解 @PreAuthorize 进行更加细度的角色或资源权限划分
                    .pathMatchers("/admin/**")
                    .hasAuthority("SCOPE_ADMIN") // 只允许后台客户端访问
                    .anyExchange()
                    .authenticated() // 其余接口认证后即可访问
            )
        .oauth2ResourceServer(
            oauth2 ->
                oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter())))
        .oauth2Client(withDefaults());
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

  //
  //  @Value("${spring.security.oauth2.resourceserver.jwt.public-key-location}")
  //  private RSAPublicKey key;

  //    @Bean
  //    public ReactiveJwtDecoder jwtDecoder() {
  //      NimbusReactiveJwtDecoder jwtDecoder =
  // NimbusReactiveJwtDecoder.withPublicKey(this.key).build();
  //      // 定义时间戳验证器或自定义验证器
  //      OAuth2TokenValidator<Jwt> withClockSkew = new DelegatingOAuth2TokenValidator<>(
  //                  new JwtTimestampValidator(Duration.ofSeconds(60)));
  //      jwtDecoder.setJwtValidator(withClockSkew);
  //      // 自定义映射 如user_name映射到sub
  //      jwtDecoder.setClaimSetConverter(...);
  //      return jwtDecoder;
  //    }
}
