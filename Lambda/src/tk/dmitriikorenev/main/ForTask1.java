package tk.dmitriikorenev.main;

import tk.dmitriikorenev.classes.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;

public class ForTask1 {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person(14, "Иван"));
        people.add(new Person(25, "Иван"));
        people.add(new Person(10, "Мария"));
        people.add(new Person(21, "Мария"));
        people.add(new Person(16, "Дмитрий"));
        people.add(new Person(45, "Дмитрий"));
        people.add(new Person(17, "Екатерина"));
        people.add(new Person(28, "Екатерина"));
        people.add(new Person(15, "Василий"));
        people.add(new Person(18, "Василий"));

        List<String> uniqueNames = people.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueNames);

        String uniqueNamesString = people.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesString);

        int maturityAge = 18;
        double averageAge = people.stream()
                .filter(p -> p.getAge() < maturityAge)
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println("Средний возраст людей моложе 18: " + averageAge);

        Map<String, Double> map = people.stream()
                .collect(Collectors.groupingBy(Person::getName, averagingInt(Person::getAge)));
        System.out.println(map);

        people.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .forEach(p -> System.out.println(p.getName()));
    }
}
