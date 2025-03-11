package ru.meeral.task16.recipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;


@Repository
public class RecipeManagerImpl implements RecipeManager{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RecipeManagerImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS recipes (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255) UNIQUE NOT NULL, " +
                "ingredients JSONB NOT NULL);";
        jdbcTemplate.getJdbcTemplate().execute(sql);
    }

    @Transactional
    public void addRecipe(String name, Map<String, String> ingredients) {
        String query = "INSERT INTO recipes (name, ingredients) VALUES (:name, CAST(:ingredients AS jsonb)) " +
                "ON CONFLICT (name) DO UPDATE SET ingredients = EXCLUDED.ingredients;";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String ingredientsJson = objectMapper.writeValueAsString(ingredients);

            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("name", name)
                    .addValue("ingredients", ingredientsJson);

            jdbcTemplate.update(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<String> findRecipeByName(String name) {
        String query = "SELECT ingredients FROM recipes WHERE name ILIKE :name";
        MapSqlParameterSource params = new MapSqlParameterSource("name", "%" + name + "%");

        return jdbcTemplate.query(query, params, (rs, rowNum) -> rs.getString("ingredients"))
                .stream()
                .findFirst();
    }

    @Transactional
    public boolean deleteRecipe(String name) {
        String query = "DELETE FROM recipes WHERE name = :name";
        int rowsAffected = jdbcTemplate.update(query, new MapSqlParameterSource("name", name));
        return rowsAffected > 0;
    }
}
