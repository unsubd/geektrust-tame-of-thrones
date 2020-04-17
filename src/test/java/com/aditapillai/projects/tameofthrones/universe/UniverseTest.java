package com.aditapillai.projects.tameofthrones.universe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {

    @Test
    void getInstance() {
        Universe first = Universe.getInstance();
        Universe second = Universe.getInstance();

        assertEquals(first, second);
        assertSame(first, second);
    }

    @Test
    void getRulingKingdom() {
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
    }

    @Test
    void setRulingKingdom() {
        Universe.getInstance()
                .setRulingKingdom(new Kingdom("PANDA", "LAND"));

        assertEquals("LAND", Universe.getInstance()
                                     .getRulingKingdom().name);

    }

    @Test
    void getKingdoms() {
        assertFalse(Universe.getInstance()
                            .getKingdoms()
                            .isEmpty());
        assertNotNull(Universe.getInstance()
                              .getKingdom("SPACE"));
    }

}