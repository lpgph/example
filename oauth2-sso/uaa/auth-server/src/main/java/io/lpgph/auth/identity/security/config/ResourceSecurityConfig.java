package io.lpgph.auth.identity.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

// 如果认证服务与授权服务分离 认证服务非资源服务器  需要单独配置资源服务
@Order(10)
@Configuration
public class ResourceSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // oauth 和 login 直接放过 其他服务属于认证之后即可请求(不需要进行oauth2授权)
    http.requestMatchers(m -> m.antMatchers("/userinfo", "/uaa/**"))
        .authorizeRequests(request -> request.anyRequest().authenticated()) // 身份认证服务的访问需要一定的权限
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .cors(withDefaults());
  }
}
