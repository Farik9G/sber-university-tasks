package ru.meeral.task17.recipe;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class RecipeConsoleWorker {
    public static void addRecipe(Scanner scanner, RecipeService recipeService) {
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

        recipeService.addRecipe(name, ingredients);
        System.out.println("Рецепт добавлен.");
    }

    public static void searchRecipe(Scanner scanner, RecipeService recipeService) {
        System.out.print("Введите название блюда или его часть: ");
        String name = scanner.nextLine();

        Optional<Recipe> recipe = recipeService.findByNameContaining(name);

        recipe.ifPresentOrElse(
                r -> {
                    System.out.println("Ингредиенты:");
                    r.getIngredients().forEach((ingredient, quantity) ->
                            System.out.println("- " + ingredient + ": " + quantity)
                    );
                },
                () -> System.out.println("Рецепт не найден.")
        );
    }

    public static void deleteRecipe(Scanner scanner, RecipeService recipeService) {
        System.out.print("Введите название блюда для удаления: ");
        String name = scanner.nextLine();

        if (recipeService.deleteRecipe(name)) {
            System.out.println("Рецепт удален.");
        } else {
            System.out.println("Рецепт не найден.");
        }
    }
}
