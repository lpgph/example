package io.lpgph.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

import java.io.Serializable;

@EnableRedisWebSession
// @EnableSpringWebSession // webflux
@Configuration
public class HttpSessionConfig {

  //  @Bean
  //  public ReactiveSessionRepository reactiveSessionRepository() {
  //    return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
  //  }

  @Bean
  public RedisTemplate<String, Serializable> redisCacheTemplate(
      LettuceConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Serializable> template = new RedisTemplate<>();
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    template.setConnectionFactory(redisConnectionFactory);
    return template;
  }

  @Bean
  public WebSessionIdResolver webSessionIdResolver() {
    SwitchWebSessionIdResolver switchWebSessionIdResolver = new SwitchWebSessionIdResolver();
    HeaderWebSessionIdResolver headerWebSessionIdResolver = new HeaderWebSessionIdResolver();
    headerWebSessionIdResolver.setHeaderName("X-Auth-Token");
    switchWebSessionIdResolver.setHeaderWebSessionIdResolver(headerWebSessionIdResolver);
    CookieWebSessionIdResolver cookieWebSessionIdResolver = new CookieWebSessionIdResolver();
    switchWebSessionIdResolver.setCookieWebSessionIdResolver(cookieWebSessionIdResolver);
    return switchWebSessionIdResolver;
  }
}
