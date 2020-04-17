package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.universe.Kingdom;
import com.aditapillai.projects.tameofthrones.universe.Universe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressRegistryTest {

    @Test
    void getKingdomFromAddress() {
        AddressRegistry registry = new AddressRegistry(Universe.getInstance()
                                                               .getKingdoms());
        Kingdom land = registry.getKingdomFromAddress("LAND");

        assertEquals("LAND", land.name);
        assertEquals("PANDA", land.emblem);
    }
}