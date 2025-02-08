package ru.sber.school.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidLength {//TODO сделать так, чтобы вешать можно было только на определённый тип
    int max();
    int min();
}
