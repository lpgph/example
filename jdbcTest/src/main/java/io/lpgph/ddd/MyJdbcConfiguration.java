package io.lpgph.ddd;

import io.lpgph.ddd.user.LongToUserIdConverter;
import io.lpgph.ddd.user.UserIdToLongConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Arrays;

@Configuration
public class MyJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    @Bean
    public JdbcCustomConversions jdbcCustomConversions() {
    return new JdbcCustomConversions(
        Arrays.asList(new LongToUserIdConverter(), new UserIdToLongConverter()));
    }
}
