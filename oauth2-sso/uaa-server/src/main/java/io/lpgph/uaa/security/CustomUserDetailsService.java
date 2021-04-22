package io.lpgph.uaa.security;

import io.lpgph.uaa.common.bean.RESTfulGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Random;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public UserDetails loadUserByUsername(String username) {
    log.info("\n\nUsernameUserDetailService   username {} \n\n", username);
    return User.builder()
        .userId(Math.abs(new Random().nextLong()))
        .username("admin")
        .password(passwordEncoder.encode("admin"))
        .authorities(
            List.of(
                new RESTfulGrantedAuthority("/**", "ALL"),
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("order/t1:read"),
                new SimpleGrantedAuthority("order/t1:write")))
        .build();
  }
}
