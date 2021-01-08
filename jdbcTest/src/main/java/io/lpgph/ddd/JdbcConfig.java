package io.lpgph.ddd;

import io.lpgph.ddd.book.converter.*;
import io.lpgph.ddd.book.model.EventTypeEnum;
import io.lpgph.ddd.common.StateEnum;

import io.lpgph.ddd.user.converter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @see org.springframework.core.convert.support.EnumToStringConverter
 */
@Configuration
public class JdbcConfig extends AbstractJdbcConfiguration {

  @Override
  public JdbcCustomConversions jdbcCustomConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();



    converters.add(new EnumToIntegerConverter());
    converters.add(new StateToIntegerConverter());
    converters.add(new IntegerToStateConverter());
    converters.add(new EventTypeToIntegerConverter());
    converters.add(new IntegerToEventTypeConverter());

    //    converters.add(new LongToPropConverter());
    //    converters.add(new PropToLongConverter());
    //
    //    converters.add(new LongToPropValueConverter());
    //    converters.add(new PropValueToLongConverter());

    //    converters.add(new LongToUserIdConverter());
    //    converters.add(new UserIdToLongConverter());

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
