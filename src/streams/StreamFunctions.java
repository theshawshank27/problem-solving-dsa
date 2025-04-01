package streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
public class StreamFunctions {

        public static void main(String[] args) {
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

            // Using Predicate functional interface
//            Predicate<String> startsWithA = name -> name.startsWith("A");
            List<String> namesStartingWithA = names.stream()
                    .filter(name -> name.startsWith("A"))
                    .collect(Collectors.toList());
            System.out.println("Names starting with A: " + namesStartingWithA);

            // Using Function functional interface
//            Function<String, Integer> nameLength = String::length;
            List<Integer> nameLengths = names.stream()
                    .map(nameLength -> nameLength.length())
                    .collect(Collectors.toList());
            System.out.println("Lengths of names: " + nameLengths);

            // Using Consumer functional interface
            Consumer<String> printName = System.out::println;
            System.out.println("Printing all names:");
            names.forEach(printName);

            // Using Optional with Stream
//            Optional<String> anyNameStartingWithD =
               names.stream()
                    .filter(name -> name.startsWith("D"))
                    .findAny()
                    .ifPresent(name -> System.out.println("Any name starting with D: " + name));
//            anyNameStartingWithD.ifPresent(name -> System.out.println("Any name starting with D: " + name));

            // Using terminal operations
            long countNamesStartingWithC = names.stream()
                    .filter(name -> name.startsWith("C"))
                    .count();
            System.out.println("Count of names starting with C: " + countNamesStartingWithC);

            List<String> sortedNames = names.stream()
                    .sorted()
                    .collect(Collectors.toList());
            System.out.println("Sorted names: " + sortedNames);

            boolean allNamesStartWithA = names.stream()
                    .allMatch(name -> name.startsWith("A"));
            System.out.println("Do all names start with A? " + allNamesStartWithA);
        }
}
