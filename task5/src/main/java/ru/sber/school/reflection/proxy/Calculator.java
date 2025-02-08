package ru.sber.school.reflection.proxy;

public interface Calculator {
    @Cache
    @Metric
    int calc(int arg);
}
