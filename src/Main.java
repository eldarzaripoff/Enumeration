import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(names.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        long count = persons.stream()
                .filter(person -> person.getAge() < 18).count();
        System.out.println(count);
        List<String> recruits = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN)).
                filter(person -> person.getAge() >= 18 && person.getAge() <= 27).map(Person::getFamily).
                collect(Collectors.toList());
        System.out.println(recruits);
        List<String> resources = persons.stream()
                .filter(person -> person.getAge() > 18).filter(person -> {
                            if (person.getSex() == Sex.MAN) {
                                return person.getAge() <= 65;
                            } else {
                                return person.getAge() <= 60;
                            }
                        }
                ).map(Person::getFamily).collect(Collectors.toList());
        System.out.println(resources);
    }
}