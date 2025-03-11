package ru.meeral.task16.recipeConsoleWorker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.meeral.task16.recipe.RecipeManager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RecipeConsoleWorker {
    public static void addRecipe(Scanner scanner, RecipeManager recipeManager) {
        System.out.print("Введите название блюда: ");
        String name = scanner.nextLine();

        Map<String, String> ingredients = new LinkedHashMap<>();
        while (true) {
            System.out.print("Введите ингредиент (или 'готово' для завершения): ");
            String ingredient = scanner.nextLine();
            if (ingredient.equalsIgnoreCase("готово")) break;
            System.out.print("Введите количество: ");
            String quantity = scanner.nextLine();
            ingredients.put(ingredient, quantity);
        }

        recipeManager.addRecipe(name, ingredients);
        System.out.println("Рецепт добавлен.");
    }

    public static void searchRecipe(Scanner scanner, RecipeManager recipeManager) {
        System.out.print("Введите название блюда или его часть: ");
        String name = scanner.nextLine();

        Optional<String> ingredientsJson = recipeManager.findRecipeByName(name);

        if (ingredientsJson.isPresent()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> ingredients = objectMapper.readValue(ingredientsJson.get(), new TypeReference<>() {});
                System.out.println("Ингредиенты:");
                ingredients.forEach((ingredient, quantity) ->
                        System.out.println("- " + ingredient + ": " + quantity)
                );
            } catch (Exception e) {
                System.out.println("Ошибка при обработке рецепта.");
            }
        } else {
            System.out.println("Рецепт не найден.");
        }
    }
    public static void deleteRecipe(Scanner scanner, RecipeManager recipeManager) {
        System.out.print("Введите название блюда для удаления: ");
        String name = scanner.nextLine();

        if (recipeManager.deleteRecipe(name)) {
            System.out.println("Рецепт удален.");
        } else {
            System.out.println("Рецепт не найден.");
        }
    }


}
