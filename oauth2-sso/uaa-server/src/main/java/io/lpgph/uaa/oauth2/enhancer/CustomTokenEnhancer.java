package io.lpgph.uaa.oauth2.enhancer;

import io.lpgph.uaa.common.json.JsonUtil;
import io.lpgph.uaa.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/** 自定义token携带内容 */
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {

  @Override
  public OAuth2AccessToken enhance(
      OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    log.info("\n\n\n\n 认证TOKEN \n{}\n\n\n", JsonUtil.toJson(accessToken));

    log.info("\n\n\n\n 认证请求信息 \n{}\n\n\n", JsonUtil.toJson(authentication));
    Map<String, Object> additionalInfo = new HashMap<>();

    if (authentication.getUserAuthentication() != null) {
      log.info(
          "\n\n\n\n UserAuthentication \n{}\n\n\n",
          JsonUtil.toJson(authentication.getUserAuthentication()));
      User user = (User) authentication.getUserAuthentication().getPrincipal();
      additionalInfo.put("userId", user.getUserId());
    }
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    return accessToken;
  }
}
