import cacheProxy.CacheProxy;
import calculator.Calculator;
import calculator.CalculatorImpl;
import database.DataBaseHandlerImpl;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = CacheProxy.cached(new CalculatorImpl(), new DataBaseHandlerImpl());

        long startTime1 = System.currentTimeMillis();
        System.out.println("Ряд Фибоначчи для числа 21: " + calculator.fibonacci(21));
        System.out.println("Ряд Фибоначчи для числа 35: " + calculator.fibonacci(35));
        System.out.println("Ряд Фибоначчи для числа 45: " + calculator.fibonacci(45));
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Время, затраченное на работу без кэша: "
                + (System.currentTimeMillis() - startTime1) + " мc");
        System.out.println("---------------------------------------------------------------------------------------");

        long startTime2 = System.currentTimeMillis();
        System.out.println("Ряд Фибоначчи для числа 21: " + calculator.fibonacci(21));
        System.out.println("Ряд Фибоначчи для числа 35: " + calculator.fibonacci(35));
        System.out.println("Ряд Фибоначчи для числа 45: " + calculator.fibonacci(45));
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Время, затраченное на работу из кэша: "
                + (System.currentTimeMillis() - startTime2) + " мс");
        System.out.println("---------------------------------------------------------------------------------------");
    }
}
