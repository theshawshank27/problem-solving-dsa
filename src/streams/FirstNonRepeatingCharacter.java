package streams;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FirstNonRepeatingCharacter {


    public static void main(String[] args) {
        String input = "geeks for geeks";
        Optional<Character> firstNonRepeatedCharacter = findFirstNonRepeatingCharacter(input);
        if (firstNonRepeatedCharacter.isPresent()) {
            System.out.println("First non repeating character :" + firstNonRepeatedCharacter.get());
        } else {
            System.out.println("No non-repeating character found.");
        }
    }

    private static Optional<Character> findFirstNonRepeatingCharacter(String input) {
        Map<Character,Long> characterMap = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new,Collectors.counting()));
        return characterMap.entrySet()
                .stream()
                .filter(c -> c.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }
}