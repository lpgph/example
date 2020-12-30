package io.lpgph.ddd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.util.Optional;

//@Import(MyBatisJdbcConfiguration.class)
@EnableJdbcAuditing
@Configuration
public class AuditorConfig {

  @Bean
  public AuditorAware<Long> auditorProvider() {
    return () -> Optional.of(1L);
  }

  //  @Bean
  //  SqlSessionFactoryBean sqlSessionFactoryBean() {
  //    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
  //    Configuration configuration =
  //        new Configuration();
  //    configuration.setLogImpl(Slf4jImpl.class);
  //    sqlSessionFactoryBean.setConfiguration(configuration);
  //    return sqlSessionFactoryBean;
  //  }
}
