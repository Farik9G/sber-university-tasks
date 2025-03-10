package ru.meeral.task15.calculator;

import ru.meeral.task15.cacheProxy.Cacheable;

import java.util.List;

public interface Calculator {
    @Cacheable
    List<Integer> fibonacci(int number);
}
