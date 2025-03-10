package ru.meeral.task15.cacheProxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.meeral.task15.calculator.Calculator;

@Configuration
public class CacheProxyConfig {
    @Bean
    public Calculator cachedCalculator(Calculator calculator) {
        return calculator;
    }
}
