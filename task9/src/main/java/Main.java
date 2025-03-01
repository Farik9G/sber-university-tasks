import person.Person;
import streams.Streams;

import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Александр", 21),
                new Person("Олег", 23),
                new Person("Валерий", 17));

        Map<String, Person> result = Streams.of(people)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(p.getName(), p.getAge() + 30))
                .toMap(Person::getName, p -> p);
        System.out.println("Исходный лист people:");
        System.out.println(people);
        System.out.println("Получили:");
        System.out.println(result);
        System.out.println("Исходный лист не изменился:");
        System.out.println(people);
    }
}
