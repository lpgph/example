package io.lpgph.uaa2.security.config;

import io.lpgph.uaa2.security.authentication.mobile.SmsCodeAuthenticationFilter;
import io.lpgph.uaa2.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import io.lpgph.uaa2.security.filter.AuthTestAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/** 登录只负责客户端授权登录 */
@Slf4j
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  private final PasswordEncoder passwordEncoder;

  private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

  /*
   * org.springframework.security.web.context.SecurityContextRepository;
   * org.springframework.security.web.context.HttpSessionSecurityContextRepository
   * 默认使用session来进行管理
   */

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/login.html", "/css/**", "/js/**", "/images/**");
  }

  // @formatter:off
  // 认证服务器 非资源服务器！！！
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // oauth 和 login 直接放过 其他服务属于认证之后即可请求(不需要进行oauth2授权)
    http.authorizeRequests(
            request ->
                request
                    .antMatchers("/login/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()) // 身份认证服务的访问需要一定的权限
        // 根据自己的权限规则来定制
        //                    .access("@permission.check(authentication,request)"))
        .formLogin(withDefaults())
        .logout(withDefaults())
        .cors(withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .anonymous(AbstractHttpConfigurer::disable)
        // 解决不允许显示在iframe的问题
        .headers(headers -> headers.frameOptions().disable().cacheControl());
    //    http.addFilter(new TestAuthenticationFilter(authenticationManager()));
    http.addFilterBefore(new AuthTestAuthenticationFilter(), BasicAuthenticationFilter.class);

    smsCodeAuthenticationSecurityConfig.configure(http);
  }

  @Bean
  AuthorityAuthorizationManager permission() {
    return new AuthorityAuthorizationManager();
  }

  /** 用户验证 */
  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder
        .userDetailsService(this.userDetailsService)
        .passwordEncoder(passwordEncoder); // 账号密码登录时重新刷新密码
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
