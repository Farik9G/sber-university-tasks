package ru.meeral.task16.recipe;

import java.util.Map;
import java.util.Optional;

public interface RecipeManager {
    void addRecipe(String name, Map<String, String> ingredients);
    Optional<String> findRecipeByName(String name);
    boolean deleteRecipe(String name);
}
