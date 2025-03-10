package ru.meeral.task15.jdbc;

import lombok.Getter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.meeral.task15.dataBase.DataSource;

@Component
public class SpringJdbcTemplate {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}