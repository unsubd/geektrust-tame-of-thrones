package com.aditapillai.projects.tameofthrones.computation;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringCompareUtils {

    public static boolean containsIndexInsensitive(String message, String emblem) {
        Map<Character, Long> charCountMap = countMap(message);
        return emblem.length() == emblem.chars()
                                        .mapToObj(character -> (char) character)
                                        .filter(character -> subtractCharacter(charCountMap, character))
                                        .count();
    }

    public static Map<Character, Long> countMap(String message) {
        return message.chars()
                      .mapToObj(character -> (char) character)
                      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }


    private static boolean subtractCharacter(Map<Character, Long> charMap, Character character) {
        return charMap.compute(character, (key, value) -> Optional.ofNullable(value)
                                                                  .map(val -> --val)
                                                                  .orElse(-1L)) >= 0;
    }
}
