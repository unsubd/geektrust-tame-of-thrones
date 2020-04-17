package com.aditapillai.projects.tameofthrones.universe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {

    @Test
    void allTests() {
        Universe first = Universe.getInstance();
        Universe second = Universe.getInstance();

        assertEquals(first, second);
        assertSame(first, second);

        assertNull(Universe.getInstance()
                           .getRulingKingdom());
        boolean result = false;
        try {
            Universe.getInstance()
                    .setRulingKingdom(null);
        } catch (NullPointerException nullPointerException) {
            result = true;
        }
        assertTrue(result);

        Universe.getInstance()
                .setRulingKingdom(new Kingdom("PANDA", "LAND"));

        assertEquals("LAND", Universe.getInstance()
                                     .getRulingKingdom().name);


        assertFalse(Universe.getInstance()
                            .getKingdoms()
                            .isEmpty());
        assertNotNull(Universe.getInstance()
                              .getKingdom("SPACE"));
    }

}