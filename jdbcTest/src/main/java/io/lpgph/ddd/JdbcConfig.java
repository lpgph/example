package io.lpgph.ddd;

import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.event.converter.DomainEventToStringConverter;
import io.lpgph.ddd.event.converter.StringToDomainEventConverter;
import io.lpgph.ddd.user.converter.*;
import io.lpgph.ddd.user.model.CreateUserEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JdbcConfig extends AbstractJdbcConfiguration {
  //  @Bean
  //  SqlSessionFactoryBean sqlSessionFactoryBean() {
  //    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
  //    org.apache.ibatis.session.Configuration configuration =
  //        new org.apache.ibatis.session.Configuration();
  //    configuration.setLogImpl(Slf4jImpl.class);
  //    sqlSessionFactoryBean.setConfiguration(configuration);
  //    return sqlSessionFactoryBean;
  //  }

  @Override
  public JdbcCustomConversions jdbcCustomConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();

    converters.add(new DomainEventToStringConverter());
    converters.add(new StringToDomainEventConverter());

    converters.add(new CreateUserEventToStringConverter());
    converters.add(new StringToCreateUserEventConverter());

    converters.add(new LongToUserIdConverter());
    converters.add(new UserIdToLongConverter());

    converters.add(new UserPropToStringConverter());
    converters.add(new JsonToUserPropConverter());

    converters.add(new JsonToStringArrayConverter());
    converters.add(new StringArrayToJsonConverter());

    converters.add(new StringToUserAddressConverter());
    converters.add(new UserAddressToStringJsonConverter());

    converters.add(new JsonToObjectArrayConverter());
    converters.add(new ObjectArrayToJsonConverter());
    return new JdbcCustomConversions(converters);
  }
}
