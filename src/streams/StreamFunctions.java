package streams;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            Map<String,Integer> nameMap = names.stream()
                    .collect(Collectors.toMap(name -> name,name-> name.length()));
            nameMap.forEach((name,length) -> System.out.println("name "+ name + " : " + length ));

            nameMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

            // Using Consumer functional interface
//            Consumer<String> printName = System.out::println;
            System.out.println("Printing all names:");
            names.forEach(System.out::println);

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
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            System.out.println("Sorted names: " + sortedNames);

            boolean allNamesStartWithA = names.stream()
                    .allMatch(name -> name.startsWith("A"));
            System.out.println("Do all names start with A? " + allNamesStartWithA);

            Stream<Double> randomNumbers = Stream.generate(Math::random).limit(5);
            randomNumbers.forEach(System.out::println);

            Stream.iterate(0,i -> i+1).limit(5).forEach(System.out::println);

            List namesCaps = names.stream()
                  .peek(String::toUpperCase)
                    .collect(Collectors.toList());

            namesCaps.stream().forEach(System.out::println);

            // Find some of all numbers in list
            List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
            int sum = numbers.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            System.out.println("Sum is " + sum);
            int sum2 = numbers.stream().reduce((a,b) -> a +b).get();
            System.out.println("Sum2 is " + sum2);
            // Find average of all the numbers
            double average = numbers.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .getAsDouble();
            System.out.println("average of all the nums is "+ average);

            double average2 = numbers.stream()
                    .mapToInt(e -> e)
                    .average()
                    .getAsDouble();
            System.out.println("average of all the nums is "+ average2);

            List<Integer> numbers2 = Arrays.asList(10,20,30,40,50);
            //Square, Filter and Average of Numbers
            OptionalDouble avg = numbers2.stream()
//                    .mapToInt(e -> e)
                    .map(e -> e*e)
                    .filter(c -> c >= 100)
                    .mapToInt(e->e)
                    .average();
            System.out.println("Square, Filter and Average of Numbers is " + avg.getAsDouble());

            //Print Even & Odd Numbers using Streams

            numbers.stream()
                    .filter(n -> n % 2 == 0 )
                    .forEach(System.out::println);
            numbers.stream()
                    .filter(n -> n % 2 != 0)
                    .forEach(System.out::println);

//            Print Numbers Starts With Prefix 2 using Streams

            List<Integer> numbers3 = Arrays.asList(-2,223,224,234,789,678,123);
            List<Integer> prefixList = numbers3.stream()
                    .map(String::valueOf)
                    .filter(n -> n.startsWith("2") || n.startsWith("-2"))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            prefixList.stream().forEach(System.out::println);


//            Print Duplicate Numbers using Streams
            List<Integer> numbers4 = Arrays.asList(-2,223,223,235,235,678,123);

            List<Integer> duplicates = numbers4.stream()
                                                .collect(Collectors.groupingBy(n -> n,Collectors.counting()))
                                                .entrySet().stream()
                                                .filter(n -> n.getValue() > 1)
                                                .map(Map.Entry::getKey)
                                                .collect(Collectors.toList());
            System.out.println(duplicates);

            Set<Integer> dup = numbers4.stream()
                                        .filter(e -> Collections.frequency(numbers4,e) > 1)
                                        .collect(Collectors.toSet());
            dup.stream().forEach(System.out::println);

            String characters = "geeks for geeks";
            Character ch = characters.chars()
                    .mapToObj(c ->(char) c)
                    .collect(Collectors.groupingBy(s -> s,LinkedHashMap::new,Collectors.counting()))
                    .entrySet().stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .findFirst().get();
            System.out.println("First non repeating character is " + ch);

// max and min
            List<Integer> numbers5 = Arrays.asList(-2,223,223,235,235,678,123);
            int min = numbers5.stream()
                    .mapToInt(n -> n)
                    .min().getAsInt();
            System.out.println("Min value is " + min);
            int max = numbers5.stream()
                                .mapToInt(n -> n)
                                .max().getAsInt();
            System.out.println("Max value is " + max);

            // sort numners

            List<Integer> numbers6 = Arrays.asList(-2,223,223,235,235,678,123);
            System.out.println("Ascending order");
            numbers6.stream().sorted().collect(Collectors.toList()).stream().forEach(System.out::println);
            System.out.println("Descending order");
            numbers6.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList()).stream().forEach(System.out::println);


//            Get/ignore first 5 numbers using Limit & Skip in Streams

              int sum1 = numbers6.stream().limit(5).collect(Collectors.toList()).stream().mapToInt(Integer::intValue).sum();
            System.out.println("sum of first 5 numbers is "+ sum1);

            int sum3 = numbers6.stream().skip(5).collect(Collectors.toList()).stream().mapToInt(Integer::intValue).sum();
            System.out.println("sum of all elements after skipping 5 elements "+ sum3);

//            Get Second Highest/Lowest Number using Streams

        int secondMin = numbers6.stream().sorted().distinct().limit(2).skip(1).findFirst().get();
            System.out.println("Second min value is " + secondMin);
        int secondMax = numbers6.stream().sorted(Collections.reverseOrder()).distinct().limit(2).skip(1).findFirst().get();
            System.out.println("Second Max value is "+ secondMax);
        }
}
