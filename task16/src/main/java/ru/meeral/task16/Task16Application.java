package ru.meeral.task16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.meeral.task16.recipe.RecipeManager;
import ru.meeral.task16.recipeConsoleWorker.RecipeConsoleWorker;

import java.util.Scanner;

@SpringBootApplication
public class Task16Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Task16Application.class, args);
        RecipeManager recipeManager = context.getBean(RecipeManager.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить рецепт");
            System.out.println("2. Найти рецепт");
            System.out.println("3. Удалить рецепт");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> RecipeConsoleWorker.addRecipe(scanner, recipeManager);
                case 2 -> RecipeConsoleWorker.searchRecipe(scanner, recipeManager);
                case 3 -> RecipeConsoleWorker.deleteRecipe(scanner, recipeManager);
                case 4 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Некорректный выбор.");
            }
        }

    }

}
