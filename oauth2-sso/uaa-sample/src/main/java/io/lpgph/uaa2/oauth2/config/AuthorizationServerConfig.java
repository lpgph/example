package io.lpgph.uaa2.oauth2.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.*;

/**
 * @see
 *     org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
 */
@Slf4j
@AllArgsConstructor
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  private final ClientDetailsService clientDetailsService;

  private final AuthorizationServerTokenServices jwtTokenServices;

  private final PasswordEncoder passwordEncoder;

  /**
   * 授权端点开放
   *
   * @param security
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    security
        .passwordEncoder(passwordEncoder) // 设置oauth_client_details中的密码编码器
        // 开启/oauth/token_key  验证端口认证权限访问
        .tokenKeyAccess("permitAll()")
        // 开启/oauth/check_token 验证端口权限访问
        .checkTokenAccess("permitAll()") // isAuthenticated()
        // 支持客户端表单认证
        .allowFormAuthenticationForClients();
  }

  /** 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息 */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(clientDetailsService);
  }

  /** 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
        //        .authenticationManager(authenticationManager) // 密码授权模式需要
        .tokenServices(jwtTokenServices)
//            .tokenStore()
//            .accessTokenConverter()
//            .tokenEnhancer()
        .reuseRefreshTokens(false) // 刷新token是否重复使用
        //        .requestFactory(defaultOAuth2RequestFactory())
        .userDetailsService(userDetailsService); // 刷新令牌需要
  }

  //  public OAuth2RequestFactory defaultOAuth2RequestFactory() {
  //    DefaultOAuth2RequestFactory defaultOAuth2RequestFactory =
  //        new DefaultOAuth2RequestFactory(clientDetailsService);
  //    defaultOAuth2RequestFactory.setCheckUserScopes(true);
  //    return defaultOAuth2RequestFactory;
  //  }
}
