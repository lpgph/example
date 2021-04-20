package io.lpgph.uaa2.security.authentication.json;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JsonAuthenticationSecurityConfig
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

//  @Autowired private AuthenticationSuccessHandler loginAuthenticationSuccessHandler;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter =
        new JsonUsernamePasswordAuthenticationFilter();
    // 设置AuthenticationManager
    jsonUsernamePasswordAuthenticationFilter.setAuthenticationManager(
        http.getSharedObject(AuthenticationManager.class));
    // 设置成功失败处理器
//    jsonUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(
//        loginAuthenticationSuccessHandler);
    jsonUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/login/json");
    // jsonAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationSuccessHandler);

    http.addFilterAfter(
        jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
