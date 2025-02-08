package ru.meeral.listPerformanceTest;

import java.util.*;

public class ListPerformanceTest {
    public static void insertValues(List<Integer> list, int size) {
        long startTime = System.nanoTime();
        for (int i = 0; i<size; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        System.out.printf("Время заполнения %s %,d элементов: %.3f секунд\n",
                list.getClass().getName(), size, (endTime - startTime)/1_000_000_000.0);
    }

    public static void testIndexSearch(List<Integer> list, int index) {
        long startTime = System.nanoTime();
        int element = list.get(index);
        long endTime = System.nanoTime();
        System.out.printf("Элемент с индексом %d был найден в %s за %.7f секунд\n",
                index, list.getClass().getName(), (endTime - startTime)/1_000_000_000.0);
    }

    public static void testIndexDelete(List<Integer> list, int index) {
        long startTime = System.nanoTime();
        list.remove(index);
        long endTime = System.nanoTime();
        System.out.printf("Элемент с индексом %d был удалён в %s за %.7f секунд\n",
                index, list.getClass().getName(), (endTime - startTime)/1_000_000_000.0);
    }
}
