package io.lpgph.auth.oauth2;

import io.lpgph.auth.common.bean.RESTfulGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CustomClientDetailsService implements ClientDetailsService {

  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    BaseClientDetails clientDetails = new BaseClientDetails();

    switch (clientId) {
      case "client1":
        clientDetails.setClientId("client1");
        clientDetails.setClientSecret(passwordEncoder.encode("client1"));
        clientDetails.setAuthorizedGrantTypes(List.of("authorization_code", "refresh_token"));
        clientDetails.setScope(List.of("all", "mall"));
        clientDetails.setAutoApproveScopes(List.of("all", "mall")); // 自动授权的scope
        clientDetails.setAccessTokenValiditySeconds(60 * 60 * 2);
        clientDetails.setRefreshTokenValiditySeconds(60 * 60 * 24);
        // 授权模式 认证成功够跳转跳转页面 并携带授权码
        clientDetails.setRegisteredRedirectUri(
            Set.of(
                "http://localhost:8081/login/oauth2/code/c1",
                "http://localhost:8082/login/oauth2/code/cms"));
        clientDetails.setAdditionalInformation(Map.of("admin", true));
        break;
      case "client2":
        clientDetails.setClientId("client2");
        clientDetails.setClientSecret(passwordEncoder.encode("client2"));
        clientDetails.setAuthorizedGrantTypes(List.of("authorization_code", "refresh_token"));
        clientDetails.setScope(List.of("all"));
        clientDetails.setAutoApproveScopes(List.of("all")); // 自动授权的scope
        clientDetails.setAccessTokenValiditySeconds(60);
        clientDetails.setRefreshTokenValiditySeconds(60 * 2);
        // 授权模式 认证成功够跳转跳转页面 并携带授权码
        clientDetails.setRegisteredRedirectUri(
            Set.of(
                // "http://localhost:8082/login/oauth2/code/c2",
                "http://192.168.0.173:8082/login/oauth2/code/c2",
                "http://localhost:8083/login/oauth2/code/mall"));
        break;
      case "mobile":
        clientDetails.setClientId("mobile");
        clientDetails.setClientSecret(passwordEncoder.encode("mobile"));
        clientDetails.setAuthorizedGrantTypes(List.of("authorization_code", "refresh_token"));
        clientDetails.setScope(List.of("all"));
        clientDetails.setAutoApproveScopes(List.of("all")); // 自动授权的scope
        clientDetails.setAccessTokenValiditySeconds(60 * 60 * 2);
        clientDetails.setRefreshTokenValiditySeconds(60 * 60 * 24);
        // 授权模式 认证成功够跳转跳转页面 并携带授权码
        clientDetails.setRegisteredRedirectUri(Set.of("http://localhost:8081/callback"));
        break;
      case "app":
        clientDetails.setClientId("app");
        clientDetails.setClientSecret(passwordEncoder.encode("app"));
        clientDetails.setAuthorizedGrantTypes(List.of("password", "refresh_token"));
        clientDetails.setScope(List.of("all"));
        clientDetails.setAccessTokenValiditySeconds(60 * 60 * 2);
        clientDetails.setRefreshTokenValiditySeconds(60 * 60 * 24);
        clientDetails.setAuthorities(
            List.of(
                new RESTfulGrantedAuthority("/a", "GET"),
                new RESTfulGrantedAuthority("/b", "POST"),
                new RESTfulGrantedAuthority("/c", "GET")));
        break;
      case "user":
        clientDetails.setClientId("user");
        clientDetails.setClientSecret(passwordEncoder.encode("user"));
        clientDetails.setAuthorizedGrantTypes(List.of("client_credentials", "refresh_token"));
        clientDetails.setAccessTokenValiditySeconds(60 * 60 * 24 * 30);
        clientDetails.setRefreshTokenValiditySeconds(60 * 60 * 24 * 180);
        clientDetails.setAutoApproveScopes(List.of("read", "write"));
        clientDetails.setAuthorities(List.of(new RESTfulGrantedAuthority("/**", "ALL")));
        clientDetails.setScope(List.of("read", "write", "user:write"));
        break;
      default:
        clientDetails.setClientId("order");
        clientDetails.setClientSecret(passwordEncoder.encode("order"));
        clientDetails.setAuthorizedGrantTypes(List.of("client_credentials", "refresh_token"));
        clientDetails.setAccessTokenValiditySeconds(60 * 60 * 24 * 30);
        clientDetails.setRefreshTokenValiditySeconds(60 * 60 * 24 * 180);
        clientDetails.setAutoApproveScopes(List.of("read", "write"));
        clientDetails.setAuthorities(List.of(new RESTfulGrantedAuthority("/**", "**")));
        clientDetails.setScope(List.of("read", "write", "order:write"));
    }

    return clientDetails;
  }
}
