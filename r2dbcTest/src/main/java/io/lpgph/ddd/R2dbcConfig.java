package io.lpgph.ddd;

import io.lpgph.ddd.user.LongToUserIdConverter;
import io.lpgph.ddd.user.UserIdToLongConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Configuration
@EnableR2dbcAuditing
public class R2dbcConfig {

    @Bean
    public ReactiveAuditorAware<Long> auditorProvider() {
        return () -> {
            return Mono.just(1L);
//        return ReactiveSecurityContextHolder.getContext()
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(User.class::cast);
        };
    }


    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return new R2dbcCustomConversions(
                Arrays.asList(new LongToUserIdConverter(), new UserIdToLongConverter()));
    }


}
