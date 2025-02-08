package ru.sber.school.reflection.proxy;

import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;

public class MainDynamic {

    public static void main(String[] args) {
        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CachedInvocationHandler(delegate));
        run(calculator);

        System.out.println("\nAll methods:");
        Class<?> clazz = CalculatorImpl.class;
        while (clazz != null) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + " " + method.getName());
            }
            clazz = clazz.getSuperclass();
        }

        System.out.println("\nGetters:");
        for (Method method : CalculatorImpl.class.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers()) && method.getName().startsWith("get") && method.getParameterCount() == 0) {
                System.out.println(method.getName());
            }
        }

        System.out.println("\nChecking String constants:");
        for (Field field : CalculatorImpl.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getType().equals(String.class)) {
                try {
                    String value = (String) field.get(null);
                    if (!field.getName().equals(value)) {
                        System.out.println("Mismatch: " + field.getName() + " != " + value);
                    } else {
                        System.out.println("Valid: " + field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        Calculator performanceCalculator = new CalculatorImpl();
        Calculator proxy = PerformanceProxy.createProxy(calculator);
        System.out.println(proxy.calc(3));
    }



    private static void run(Calculator calculator) {
        System.out.println("START:"+LocalDateTime.now());
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(5));
        System.out.println(calculator.calc(6));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println("END:"+LocalDateTime.now());
    }
}
