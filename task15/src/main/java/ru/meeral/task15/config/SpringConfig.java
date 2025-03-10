package ru.meeral.task15.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.meeral.task15.calculator", "ru.meeral.task15.dataBase",
        "ru.meeral.task15.cacheProxy"})
public class SpringConfig {
}