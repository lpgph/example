package io.lpgph.uaa3.identity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.io.Serializable;

@Configuration
@EnableRedisHttpSession
//@EnableSpringHttpSession
public class HttpSessionConfig {

  ////  https://github.com/spring-guides/tut-spring-security-and-angular-js.git
    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(
        LettuceConnectionFactory redisConnectionFactory) {
      RedisTemplate<String, Serializable> template = new RedisTemplate<>();
      template.setKeySerializer(new StringRedisSerializer());
      template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
      template.setConnectionFactory(redisConnectionFactory);
      return template;
    }

//  @Bean
//  public MapSessionRepository sessionRepository() {
//    return new MapSessionRepository(new ConcurrentHashMap<>());
//  }

  // override the default of using cookies and instead use headers
//  @Bean
//  public HttpSessionIdResolver httpSessionIdResolver() {
//    return HeaderHttpSessionIdResolver.xAuthToken();
//    //    return new CookieHttpSessionIdResolver();
//  }
}
