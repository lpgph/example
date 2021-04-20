package io.lpgph.uaa2.oauth2.config;

import io.lpgph.uaa2.oauth2.CustomClientDetailsService;
import io.lpgph.uaa2.oauth2.enhancer.CustomTokenEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.Arrays;

@Configuration
public class BasicOauth2Config {

  @Bean
  public ClientDetailsService clientDetailsService() {
    return new CustomClientDetailsService();
  }

  /**
   * 是用来生成token的转换器，而token令牌默认是有签名的，且资源服务器需要验证这个签名。加密及验签包括两种方式： 对称加密、非对称加密（公钥密钥） 此处使用非对称加密
   *
   * @see org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint
   */
  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    /*
     * 通过 JDK 工具生成 JKS 证书文件，并将 keystore.jks 放入resource目录下
     * keytool -genkeypair -alias test -keyalg RSA -dname "CN=Web Server,OU=lpgph,O=lpgph.github.io,L=BeiJing,S=BeiJing,C=CN" -keypass testpass -storetype PKCS12 -keystore ./test.p12 -storepass testpass
     * 生成P12 SSL使用
     * keytool -genkeypair -alias test -keyalg RSA -dname "CN=Web Server,OU=lpgph,O=lpgph.github.io,L=BeiJing,S=BeiJing,C=CN" -storetype PKCS12 -keystore ./test.p12 -storepass testpass
     * // P12 生成jks
     * keytool -importkeystore -srckeystore keystore.p12 -srcstoretype PKCS12 -deststoretype JKS -destkeystore keystore.jks
     * // jsk转P12
     * keytool -importkeystore -srckeystore keystore.jks -srcstoretype JKS -deststoretype PKCS12 -destkeystore keystore.p12
     * // 生成证书
     * keytool -export -alias test -keystore ./test.jks  -storetype PKCS12 -storepass testpass -rfc -file ./test.cer
     * // 生成公钥和证书
     * keytool -list -rfc --keystore ./test.jks| openssl x509 -inform pem -pubkey
     * */
    KeyPair keyPair =
        new KeyStoreKeyFactory(new ClassPathResource("test.jks"), "testpass".toCharArray())
            .getKeyPair("test");
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setKeyPair(keyPair);
    DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
    DefaultUserAuthenticationConverter userAuthenticationConverter =
        new DefaultUserAuthenticationConverter();
    userAuthenticationConverter.setUserClaimName("sub");
    accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
    converter.setAccessTokenConverter(accessTokenConverter);
    return converter;
  }

  @Bean
  public TokenStore jwtTokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  /** @see org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint */
  @Bean
  public DefaultTokenServices jwtTokenServices(TokenStore jwtTokenStore) {
    DefaultTokenServices services = new DefaultTokenServices();
    services.setClientDetailsService(clientDetailsService());
    services.setSupportRefreshToken(true);
    //    services.setReuseRefreshToken(false);
    services.setTokenStore(jwtTokenStore);
    services.setAccessTokenValiditySeconds(60 * 60 * 2);
    services.setRefreshTokenValiditySeconds(60 * 60 * 24);

    // token过滤链
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    // 注意前后顺序
    tokenEnhancerChain.setTokenEnhancers(
        Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter()));
    services.setTokenEnhancer(tokenEnhancerChain);
    return services;
  }
}
