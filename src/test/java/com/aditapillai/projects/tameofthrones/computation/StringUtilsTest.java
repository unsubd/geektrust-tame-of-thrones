package com.aditapillai.projects.tameofthrones.computation;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    public void textContainsAllCharactersOfWord_TrueReturned() {
        boolean result = StringUtils.textContainsAllCharactersOfWord("THIS IS A MESSAGE", "SAGEHIS");
        assertTrue(result);
    }

    @Test
    public void textDoesNotContainAllCharactersOfWord_FalseReturned() {
        boolean result = StringUtils.textContainsAllCharactersOfWord("THIS IS A MESSAGE", "SYSTEM");
        assertFalse(result);
    }

    @Test
    public void countMap_MESSAGE_computedMapReturned() {
        //Expected
        Map<Character, Long> countMap = new HashMap<>();
        countMap.put('M', 1L);
        countMap.put('A', 1L);
        countMap.put('E', 2L);
        countMap.put('S', 2L);
        countMap.put('G', 1L);

        assertEquals(countMap, StringUtils.countMap("MESSAGE"));
    }

}