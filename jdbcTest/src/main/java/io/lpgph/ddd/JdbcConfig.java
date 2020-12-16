package io.lpgph.ddd;

import io.lpgph.ddd.book.converter.*;
import io.lpgph.ddd.user.LongToUserIdConverter;
import io.lpgph.ddd.user.UserIdToLongConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.MyBatisJdbcConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableJdbcAuditing
@EnableJdbcRepositories
@Import(MyBatisJdbcConfiguration.class)
public class JdbcConfig {

  //  @Bean
  //  SqlSessionFactoryBean sqlSessionFactoryBean() {
  //    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
  //    org.apache.ibatis.session.Configuration configuration =
  //        new org.apache.ibatis.session.Configuration();
  //    configuration.setLogImpl(Slf4jImpl.class);
  //    sqlSessionFactoryBean.setConfiguration(configuration);
  //    return sqlSessionFactoryBean;
  //  }

  @Bean
  public AuditorAware<Long> auditorProvider() {
    return () -> Optional.of(1L);
  }

  @Bean
  public JdbcCustomConversions jdbcCustomConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();
    converters.add(new LongToUserIdConverter());
    converters.add(new UserIdToLongConverter());

    converters.add(new BookAdToJsonConverter());
    converters.add(new JsonToBookAdConverter());

    converters.add(new JsonToStringArrayConverter());
    converters.add(new StringArrayToJsonConverter());

    converters.add(new JsonToObjectArrayConverter());
    converters.add(new ObjectArrayToJsonConverter());
    return new JdbcCustomConversions(converters);
  }
}
