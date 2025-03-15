package ru.meeral.task18.jdbc;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.meeral.task18.database.DataSource;

@Getter
@Component
public class SpringJdbcTemplate {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

}