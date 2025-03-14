package ru.meeral.task17.recipe;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findByNameContainingIgnoreCase(String name);
}
