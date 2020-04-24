package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.models.Gender;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import com.aditapillai.projects.tameofthrones.universe.Kingdom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressRegistryTest {

    private AddressRegistry registry;
    private Kingdom lava;
    private Kingdom water;


    @BeforeEach
    public void setUp() {
        lava = new Kingdom("DOLPHIN", "LAVA", new Ruler("King King", Gender.MALE));
        water = new Kingdom("WHALE", "WATER", new Ruler("Queen Mermaid", Gender.FEMALE));

        List<Kingdom> kingdoms = Arrays.asList(lava);
        this.registry = new AddressRegistry(kingdoms);
    }

    @Test
    public void addKingdom_NewKingdom_Added() {
        assertTrue(registry.addKingdom(water));
    }

    @Test
    public void addKingdom_ExistingKingdom_NotAdded() {
        assertFalse(registry.addKingdom(lava));
    }

    @Test
    public void getKingdomFromName_ValidName_Returned() {
        assertEquals(this.lava, registry.getKingdomFromName("LAVA")
                                        .get());
    }

    @Test
    public void getKingdomFromName_InvalidName_NullReturned() {
        assertFalse(registry.getKingdomFromName("INVALID")
                            .isPresent());
    }

}