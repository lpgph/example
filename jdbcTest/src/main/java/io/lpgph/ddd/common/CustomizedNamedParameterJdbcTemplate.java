package io.lpgph.ddd.common;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Arrays;
import java.util.function.Consumer;

public class CustomizedNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate {

    public CustomizedNamedParameterJdbcTemplate(JdbcOperations classicJdbcTemplate) {
        super(classicJdbcTemplate);
    }

    @Override
    protected PreparedStatementCreator getPreparedStatementCreator(String sql, SqlParameterSource paramSource, Consumer<PreparedStatementCreatorFactory> customizer) {
        SqlParameterSource newParamSource =  new MapSqlParameterSource();
        Arrays.stream(paramSource.getParameterNames()).forEach(name->{


        });
        return super.getPreparedStatementCreator(sql, paramSource, customizer);
    }
}
