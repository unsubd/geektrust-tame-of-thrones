package com.aditapillai.projects.tameofthrones.computation;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class StringUtils {

    /**
     * Compares whether all the characters in word are present in the text
     *
     * @param text to be searched
     * @param word whose characters need to be searched in the text
     * @return true only if all the characters in the word are present in the text.
     * <p>
     * Example:
     * containsIndexInsensitive("fork", "rofk") -> true
     * containsIndexInsensitive("fork", "roofk") -> false
     */
    public static boolean textContainsAllCharactersOfWord(String text, String word) {
        Map<Character, Long> charCountMap = countMap(text);
        return word.length() == word.chars()
                                    .mapToObj(character -> (char) character)
                                    .filter(character -> subtractCharacterFromMap(charCountMap, character))
                                    .count();
    }

    /**
     * Given a string, return the count map for it's characters
     *
     * @param text text
     * @return count map of the string
     */
    public static Map<Character, Long> countMap(String text) {
        return text.chars()
                   .mapToObj(character -> (char) character)
                   .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Return a space separated representation of a collection of elements
     *
     * @param elements all the elements as a collection
     * @param <T>      Type of the elements
     * @return space separated representation of the collection
     */
    public static <T> String collectionToSpaceSeparatedString(Collection<T> elements) {
        return elements.stream()
                       .collect(StringBuilder::new, (acc, current) -> {
                           acc.append(" ");
                           acc.append(current);
                       }, StringBuilder::append)
                       .toString()
                       .trim();
    }


    private static boolean subtractCharacterFromMap(Map<Character, Long> countMap, Character character) {
        return countMap.compute(character, (key, value) -> Optional.ofNullable(value)
                                                                   .map(val -> --val)
                                                                   .orElse(-1L)) >= 0;
    }
}
