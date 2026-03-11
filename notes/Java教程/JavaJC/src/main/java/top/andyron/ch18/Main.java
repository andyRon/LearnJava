package top.andyron.ch18;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(evenNumbers);

        List<String> stringCollection = Arrays.asList("bba", "aab", "cccc", "ddd", "aae", "afd", "ddd2", "aah");
        stringCollection.stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        stringCollection.stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        stringCollection.stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);
    }
}
