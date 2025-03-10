import cacheProxy.CacheProxy;
import calculator.Calculator;
import calculator.CalculatorImpl;
import database.DataBaseHandlerImpl;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = CacheProxy.cached(new CalculatorImpl(), new DataBaseHandlerImpl());

        System.out.println("Тестирование вычисления ряда Фибоначчи:");

        long coldStartTime = measureExecutionTime(calculator, "Вычисления без кэша");

        long cachedStartTime = measureExecutionTime(calculator, "Вычисления с кэшем");

        System.out.printf("\nСкорость выполнения с кэшем быстрее в %.2f раза!\n",
                (double) coldStartTime / cachedStartTime);
    }

    private static long measureExecutionTime(Calculator calculator, String description) {
        long startTime = System.currentTimeMillis();

        System.out.println("\n" + "-".repeat(80));
        System.out.println(description);
        System.out.println("-".repeat(80));

        int[] testNumbers = {21, 35, 45};
        for (int number : testNumbers) {
            System.out.printf("Ряд Фибоначчи для числа %d: %s%n", number, calculator.fibonacci(number));
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.printf("\nВремя выполнения: %d мс%n", elapsedTime);
        System.out.println("-".repeat(80));

        return elapsedTime;
    }
}
