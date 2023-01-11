import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 1_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long numberOfUnderages = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("numberOfUnderages = " + numberOfUnderages);

        List<String> surnamesOfRecruits = persons.stream()
                .filter(person -> person.getAge() > 17 && person.getAge() < 28 && person.getSex() == Sex.MAN)
                .map(person -> person.getSurname())
                .collect(Collectors.toList());
        System.out.println("surnamesOfRecruits: " + surnamesOfRecruits);

        Collection<Person> potentialWorkers = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex() == Sex.WOMAN) && (person.getAge() > 17 && person.getAge() < 61) ||
                        (person.getSex() == Sex.MAN) && (person.getAge() > 17 && person.getAge() < 66))
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());
        System.out.println("potentialWorkers: \n");
        for(Person potentialWorker : potentialWorkers) {
            System.out.println(potentialWorker);
        }

    }
}