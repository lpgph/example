package io.lpgph.ddd.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
//@EnableJdbcAuditing
public class BaseConfig {

  @Bean
  public AuditorAware<Long> auditorProvider() {
    return () -> Optional.of(1L);
    // 从 request 或者 session 中取
    //        try {
    //            // IllegalStateException
    //            RequestAttributes requestAttributes =
    // RequestContextHolder.currentRequestAttributes();
    //            Object userIdObject = requestAttributes.getAttribute("userId",
    // RequestAttributes.SCOPE_SESSION);
    //            if (userIdObject == null) {
    //                return Optional.empty();
    //            }
    //            // ClassCastException
    //            Long userId = (Long) userIdObject;
    //            return Optional.of(userId);
    //        } catch (IllegalStateException | ClassCastException e) {
    //            return Optional.empty();
    //        }

    // 当集成了 Security 的时候，可以从 SecurityContextHolder 取得
//    try {
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      UserAuth auth = (UserAuth) authentication.getPrincipal();
//      return Optional.of(auth.getUserId());
//    } catch (ClassCastException e) {
//      return Optional.empty();
//    }
  }
}
