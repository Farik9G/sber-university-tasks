package ru.meeral.task15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.meeral.task15.calculator.Calculator;
import ru.meeral.task15.calculator.CalculatorImpl;
import ru.meeral.task15.dataBase.DataBaseHandler;

import java.util.List;

@SpringBootApplication
public class Task15Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Task15Application.class, args);
        DataBaseHandler dataBaseHandler = context.getBean(DataBaseHandler.class);

        Calculator calculator = context.getBean(CalculatorImpl.class);

        int[] testNumbers = {21, 35, 45};

        long startTime1 = System.currentTimeMillis();
        for (int num : testNumbers) {
            List<Integer> result = calculator.fibonacci(num);
            dataBaseHandler.saveData(num, result);
            System.out.println("Ряд Фибоначчи для числа " + num + ": " + result);
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Время без кэша: " + (System.currentTimeMillis() - startTime1) + " мс");
        System.out.println("---------------------------------------------------------------------------------------");

        long startTime2 = System.currentTimeMillis();
        for (int num : testNumbers) {
            List<Integer> cachedResult = dataBaseHandler.loadData(num);
            System.out.println("Ряд Фибоначчи (из БД) для числа " + num + ": " + cachedResult);
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Время из кэша: " + (System.currentTimeMillis() - startTime2) + " мс");
        System.out.println("---------------------------------------------------------------------------------------");
    }
}