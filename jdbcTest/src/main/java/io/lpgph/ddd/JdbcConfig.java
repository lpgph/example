package io.lpgph.ddd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.MyBatisJdbcConfiguration;

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

}
