package com.aditapillai.projects.tameofthrones.services.utils;

import com.aditapillai.projects.tameofthrones.universe.Kingdom;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class IOUtilsTest {

    @Test
    void initialDataSetupValidation() {
        List<Kingdom> allKingdoms = IOUtils.getAllKingdoms();
        //make sure all kingdoms are loaded
        assertNotNull(allKingdoms);
        assertFalse(allKingdoms.isEmpty());
        assertEquals(6, allKingdoms.size());

        //make sure all of them have a ruler to begin with
        assertEquals(0, allKingdoms.stream()
                                   .filter(kingdom -> Objects.isNull(kingdom.getRuler()))
                                   .count());

        //none of them have allies
        assertEquals(0, allKingdoms.stream()
                                   .filter(kingdom -> !kingdom.getAllies()
                                                              .isEmpty())
                                   .count());


    }
}