package ru.sber.school.reflection.proxy;

public class CalculatorImpl implements Calculator {

    private final String name;

    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "FRIDAY";


    public CalculatorImpl() {
        this.name = "DefaultCalculator";
    }

    public String getName() {
        return name;
    }

    @Override
    @Cache
    @Metric
    public int calc(int arg) {
        System.out.println("Вызван calc:" + arg);
        try {
            System.out.println("Производятся супер сложные вычисления");
            Thread.sleep(5000);
            if (arg < 0) {
                throw new IllegalArgumentException("Факториал отрицательного числа не определен");
            }
            return factorial(arg);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void countCrow() {
        System.out.println("Вызван countCrow");
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
