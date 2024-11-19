package ru.meeral;

import ru.meeral.phoneBook.PhoneBook;

import java.util.*;

import static ru.meeral.listPerformanceTest.ListPerformanceTest.*;

public class Main {
    public static void main(String[] args) {
        String [] words = {"apple", "banana", "cherry", "apple", "date",
                "fig", "grape", "apple", "banana", "kiwi",
                "lemon", "mango", "apple", "orange", "banana",
                "peach", "quince", "raspberry", "apple", "banana"};

        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> sortedEntries = wordCount.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())).toList();

        System.out.println("---Уникальные слова и их количество---");
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }

        PhoneBook pb = new PhoneBook();

        pb.add("Крылов", "111-222-333");
        pb.add("Иванов", "563-234-123");
        pb.add("Крылов", "141-155-643");

        System.out.println("\n---Телефонный справочник---");
        System.out.printf("Крылов: %s\n", pb.get("Крылов"));
        System.out.printf("Иванов: %s\n", pb.get("Иванов"));
        System.out.printf("Петров: %s\n", pb.get("Петров"));


//        Тестируем скорость ArrayList и LinkedList

        int size = 1_000_000;
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        System.out.println("\n---Тест заполнения миллионом элементов---");
        insertValues(arrayList, size);
        insertValues(linkedList, size);

        System.out.println("\n---Тест поиска элемента в начале---");
        testIndexSearch(arrayList, 3);
        testIndexSearch(linkedList, 3);

        System.out.println("\n---Тест поиска элемента в середине---");
        testIndexSearch(arrayList, size / 2 );
        testIndexSearch(linkedList, size / 2);

        System.out.println("\n---Тест поиска элемента в конце---");
        testIndexSearch(arrayList, size - 3);
        testIndexSearch(linkedList, size - 3);

        System.out.println("\n---Тест удаления элемента в начале---");
        testIndexDelete(arrayList, 3);
        testIndexDelete(linkedList, 3);

        System.out.println("\n---Тест удаления элемента в середине---");
        testIndexDelete(arrayList, size / 2 );
        testIndexDelete(linkedList, size / 2);

        System.out.println("\n---Тест удаления элемента в конце---");
        testIndexDelete(arrayList, size - 3);
        testIndexDelete(linkedList, size - 3);
    }
}