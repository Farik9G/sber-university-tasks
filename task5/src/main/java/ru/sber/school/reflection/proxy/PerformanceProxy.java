package ru.sber.school.reflection.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PerformanceProxy implements Calculator {

    private final Calculator delegate;

    public PerformanceProxy(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public int calc(int number) {
        try {
            Method method = delegate.getClass().getMethod("calc", int.class);
            if (method.isAnnotationPresent(Metric.class)) {
                long startTime = System.nanoTime();
                int result = delegate.calc(number);
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                System.out.println("Execution time of " + method.getName() + ": " + duration + " ns");
                return result;
            }
            return delegate.calc(number);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Calculator createProxy(Calculator delegate) {
        return (Calculator) Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                new Class[] {Calculator.class},
                (proxy, method, args) -> {
                    if (method.isAnnotationPresent(Metric.class)) {
                        long startTime = System.nanoTime();
                        Object result = method.invoke(delegate, args);
                        long endTime = System.nanoTime();
                        long duration = endTime - startTime;
                        System.out.println("Время работы метода: " + duration + "(в наносек)");
                        return result;
                    }
                    return method.invoke(delegate, args);
                });
    }
}
