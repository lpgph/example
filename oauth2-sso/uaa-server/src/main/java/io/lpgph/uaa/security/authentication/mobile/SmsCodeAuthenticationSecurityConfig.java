package io.lpgph.uaa.security.authentication.mobile;

import io.lpgph.uaa.security.service.ISmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * see https://docs.spring.io/spring-security/site/docs/current/reference/html5/#jc-custom-dsls
 */
@Component
public class SmsCodeAuthenticationSecurityConfig
        extends AbstractHttpConfigurer<SmsCodeAuthenticationSecurityConfig, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;


    //  @Autowired private AuthenticationSuccessHandler loginAuthenticationSuccessHandler;

    @Autowired
    private ISmsCodeService smsCodeService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeFilter = new SmsCodeAuthenticationFilter();
        // 设置AuthenticationManager
        smsCodeFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //    smsCodeFilter.setUsernameParameter("phone");
        //    smsCodeFilter.setPasswordParameter("code");
        //    smsCodeFilter.setFilterProcessesUrl("/login/phone");
        // 设置成功失败处理器
        //    smsCodeFilter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler);
        // 设置 provider
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider =
                new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        smsCodeAuthenticationProvider.setSmsCodeService(smsCodeService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }

//  public static SmsCodeAuthenticationSecurityConfig  smsLogin(MobileUserDetailsService userDetailsService, ISmsCodeService smsCodeService) {
//    return new SmsCodeAuthenticationSecurityConfig(userDetailsService,smsCodeService);
//  }
}
