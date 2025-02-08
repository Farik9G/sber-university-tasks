package ru.meeral;


import ru.meeral.classes.CollectionUtils;
import ru.meeral.implementations.CountMapImpl;
import ru.meeral.interfaces.CountMap;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CountMap<String> countMap = new CountMapImpl<>();

        countMap.add("camera");
        countMap.add("CCTV");
        countMap.add("camera");

        System.out.printf("Количество вхождений элемента camera: %d\n", countMap.getCount("camera"));
        System.out.printf("Количество вхождений элемента CCTV: %d\n", countMap.getCount("CCTV"));
        System.out.printf("Количество вхождений элемента film: %d\n", countMap.getCount("film"));
        System.out.printf("Количество разных элементов: %d\n", countMap.size());

        countMap.remove("camera");

        System.out.printf("Количество вхождений элемента camera после удаления: %d\n", countMap.getCount("camera"));

        CountMap<String> anotherCountMap = new CountMapImpl<>();
        anotherCountMap.add("clip");
        anotherCountMap.add("operator");
        anotherCountMap.add("clip");

        System.out.printf("Количество разных элементов в countMap до добавления anotherCountMap: %d\n", countMap.size());

        countMap.addAll(anotherCountMap);

        System.out.printf("Количество разных элементов в countMap после добавления anotherCountMap: %d\n", countMap.size());

        Map<String, Integer> anotherDefaultMap = anotherCountMap.toMap();

        System.out.println("Приведение anotherCountMap к Map:");
        System.out.println(anotherDefaultMap.getClass());
        System.out.println(anotherDefaultMap);

        Map<String, Integer> destinationMap = new HashMap<>();
        countMap.toMap(destinationMap);
        System.out.println("Приведение countMap к Map с указанием destination\n" + destinationMap);

        List<Integer> source = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> destination = CollectionUtils.newArrayList();

        CollectionUtils.addAll(source, destination);
        System.out.println("Destination после добавления source: " + destination);
        System.out.println("Индекс элемента 3: " + CollectionUtils.indexOf(source, 3));
        System.out.println("Source после установления лимита в 3 элемента: " + CollectionUtils.limit(source, 3));

        CollectionUtils.add(destination, 6);

        System.out.println("Destination после добавления элемента: " + destination);
        System.out.println("Destination содержит последовательность [2, 3]: " + CollectionUtils.containsAll(destination,
                Arrays.asList(2, 3)));
        System.out.println("Destination содержит один из элементов [7, 3]: " + CollectionUtils.containsAny(destination,
                Arrays.asList(7, 3)));
        System.out.println("Destination содержит один из элементов [7, 8]: " + CollectionUtils.containsAny(destination,
                Arrays.asList(7, 8)));
        System.out.println("Range 2-4: " + CollectionUtils.range(source, 2, 4));

        Comparator<Integer> comparator = Integer::compare;

        System.out.println("Range 2-4 с использованием определённого компаратора: " + CollectionUtils.range(source,
                2, 4, comparator));
    }
}