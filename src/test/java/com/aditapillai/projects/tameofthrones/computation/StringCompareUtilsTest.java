package com.aditapillai.projects.tameofthrones.computation;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringCompareUtilsTest {

    @Test
    void containsIndexInsensitive() {
        assertTrue(StringCompareUtils.containsIndexInsensitive("MESSAGE", "MESSAGE"));
        assertTrue(StringCompareUtils.containsIndexInsensitive("fork", "rofk"));
        assertTrue(StringCompareUtils.containsIndexInsensitive("alabaminaforkkingdom", "drinking"));
    }

    @Test
    void countMap() {
        Map<Character, Long> countMap = StringCompareUtils.countMap("AAABBBCCC");
        assertEquals(3, (long) countMap.get('A'));
        assertEquals(3, (long) countMap.get('B'));
        assertEquals(3, (long) countMap.get('C'));

        countMap = StringCompareUtils.countMap("ABAB");
        assertEquals(2, (long) countMap.get('A'));
        assertEquals(2, (long) countMap.get('B'));
    }
}