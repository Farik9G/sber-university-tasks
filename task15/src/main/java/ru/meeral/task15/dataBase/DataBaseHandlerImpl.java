package ru.meeral.task15.dataBase;

import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.meeral.task15.jdbc.SpringJdbcTemplate;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class DataBaseHandlerImpl implements DataBaseHandler {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DataBaseHandlerImpl(SpringJdbcTemplate springJdbcTemplate) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(springJdbcTemplate.getJdbcTemplate());
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS fibonacci (" +
                "id SERIAL PRIMARY KEY, " +
                "number INTEGER UNIQUE, " +
                "result INTEGER[]);";
        jdbcTemplate.getJdbcTemplate().execute(sql);
    }

    @Override
    @Transactional
    public void saveData(int number, List<Integer> data) {
        String query = "INSERT INTO fibonacci (number, result) VALUES (:number, :result) " +
                "ON CONFLICT (number) DO UPDATE SET result = EXCLUDED.result;";

        try (Connection connection = jdbcTemplate.getJdbcTemplate().getDataSource().getConnection()) {
            Array sqlArray = connection.createArrayOf("INTEGER", data.toArray());
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("number", number)
                    .addValue("result", new SqlParameterValue(Types.ARRAY, sqlArray));

            jdbcTemplate.update(query, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> loadData(int number) {
        String query = "SELECT result FROM fibonacci WHERE number = :number";
        return jdbcTemplate.query(query, new MapSqlParameterSource("number", number), (rs, rowNum) -> {
            Array array = rs.getArray("result");
            return List.of((Integer[]) array.getArray());
        }).stream().findFirst().orElse(List.of());
    }
}

