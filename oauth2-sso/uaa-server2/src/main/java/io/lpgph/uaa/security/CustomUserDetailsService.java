package io.lpgph.uaa.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.stream.Collectors;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Slf4j
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  //  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  private final UserServer userServer;


  @Override
  public UserDetails loadUserByUsername(String username) {
    UserInfo userInfo = userServer.getUserByUsername(username);
    return User.builder()
        .userId(userInfo.getId())
        .username(userInfo.getUsername())
        .password(userInfo.getPassword())
        .authorities(
            userInfo.getAuthorities() == null
                ? new HashSet<>()
                : userInfo.getAuthorities().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet()))
        .build();
  }
}
