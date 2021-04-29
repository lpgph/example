package io.lpgph.uaa.security.config;

import io.lpgph.uaa.security.filter.AuthTestAuthenticationFilter;
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
import org.springframework.security.config.http.SessionCreationPolicy;
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

  /*
   * org.springframework.security.web.context.SecurityContextRepository;
   * org.springframework.security.web.context.HttpSessionSecurityContextRepository
   * 默认使用session来进行管理
   */

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/css/**", "/js/**", "/fonts/**", "/images/**", "/favicon.ico", "/webjars/**");
  }

  // @formatter:off

  /**
   * 该服务 存储用户信息 身份认证 以及权限管理 <br>
   * 作为认证服务 只提供 身份的认证 认证后返回token 如果被单独请求登录 则跳转到默认页面 <br>
   * 作为资源服务 提供给客户端通过tokenL调用 <br>
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // oauth 和 login 直接放过 其他服务属于认证之后即可请求(不需要进行oauth2授权)
    // 限定请求范围
    http
        //            .requestMatchers(m -> m.antMatchers("/login/**", "/oauth/**"))
        .authorizeRequests(
            request ->
                request
                    .antMatchers("/login/**")
                    .permitAll()
                    //                    .antMatchers("/oauth/**")
                    //                    .authenticated()
                    .anyRequest()
                    .authenticated()) // 身份认证服务的访问需要一定的权限
        // 根据自己的权限规则来定制
        //                    .access("@permission.check(authentication,request)"))
        .formLogin(withDefaults())
        //        .formLogin(
        //            login ->
        //                login
        //                    .successHandler(switchAuthenticationSuccessHandler)
        //                    .loginPage("/login.html")
        //                    .loginProcessingUrl("/login")
        //                    .failureHandler(new DefaultAuthenticationFailureHandler()))
        .logout(withDefaults())
        //         .logout(logout-> logout
        //                 .logoutSuccessHandler(new DefaultLogoutSuccessHandler()))
        // 自定义未登录结果 系统默认未登录会跳转到登录页
        //         .exceptionHandling(handling-> handling
        //                 .authenticationEntryPoint(new DefaultAuthenticationEntryPoint()))
        .cors(withDefaults())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
        .csrf(AbstractHttpConfigurer::disable)
        .anonymous(AbstractHttpConfigurer::disable)
        .oauth2Client(withDefaults())
        // 解决不允许显示在iframe的问题
        .headers(headers -> headers.frameOptions().disable().cacheControl());
    //    http.addFilter(new TestAuthenticationFilter(authenticationManager()));
    http.addFilterBefore(new AuthTestAuthenticationFilter(), BasicAuthenticationFilter.class);
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
