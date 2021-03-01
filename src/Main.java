import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Person> people = getPeople();

        // Imperative approach
        /*List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }
        females.forEach(System.out::println);*/

        // Declarative approach

        //Filter
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

        //Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);

        //All match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 10);

        //Any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 20);

        //None match
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Naziba"));

        //Max
        people.stream()
                .max(Comparator.comparing(Person::getAge));

        //Min
        people.stream()
                .min(Comparator.comparing(Person::getAge));

        //Group
        Map<Gender, List<Person>> genderListMap = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        /*genderListMap.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });*/

        System.out.println();
        System.out.print("Oldest Female - ");
        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemaleAge.ifPresent(System.out::println);

    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Sarker Akash", 30, Gender.MALE),
                new Person("Nupur Hasnin", 25, Gender.FEMALE),
                new Person("Ripon Halim", 35, Gender.MALE),
                new Person("Sarker Rony", 20, Gender.FEMALE)
        );
    }
}
