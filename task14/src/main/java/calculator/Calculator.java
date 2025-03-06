package calculator;

import cacheProxy.Cacheable;

import java.util.List;

public interface Calculator {
    @Cacheable
    List<Integer> fibonacci (int number);
}
