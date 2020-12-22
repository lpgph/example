package io.lpgph.oidc.oauth2;

import io.lpgph.oidc.common.bean.RESTfulGrantedAuthority;
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
      case "auth":
        clientDetails.setClientId("auth");
        clientDetails.setClientSecret(passwordEncoder.encode("auth"));
        clientDetails.setAuthorizedGrantTypes(List.of("authorization_code", "refresh_token"));
        clientDetails.setScope(List.of("all", "mall"));
        clientDetails.setAutoApproveScopes(List.of("all", "mall")); // 自动授权的scope
        clientDetails.setAccessTokenValiditySeconds(60 * 60 * 2);
        clientDetails.setRefreshTokenValiditySeconds(60 * 60 * 24);
        // 授权模式 认证成功够跳转跳转页面 并携带授权码
        clientDetails.setRegisteredRedirectUri(
            Set.of("http://localhost:8090/login/oauth2/code/cms"));
        clientDetails.setAdditionalInformation(Map.of("admin", true));
        break;
      default:
        clientDetails.setClientId("order");
        clientDetails.setClientSecret(passwordEncoder.encode("order"));
        clientDetails.setAuthorizedGrantTypes(List.of("client_credentials", "refresh_token"));
        clientDetails.setAccessTokenValiditySeconds(60 * 60 * 24 * 30);
        clientDetails.setRefreshTokenValiditySeconds(60 * 60 * 24 * 180);
        clientDetails.setAutoApproveScopes(List.of("read", "write"));
//        clientDetails.setAuthorities(List.of(new RESTfulGrantedAuthority("/**", "**")));
        clientDetails.setScope(List.of("read", "write", "order:write"));
    }

    return clientDetails;
  }
}
