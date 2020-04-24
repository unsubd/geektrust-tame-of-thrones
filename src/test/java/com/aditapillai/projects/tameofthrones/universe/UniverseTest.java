package com.aditapillai.projects.tameofthrones.universe;

import com.aditapillai.projects.tameofthrones.cipher.Ciphers;
import com.aditapillai.projects.tameofthrones.models.Gender;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {
    private Kingdom lava;
    private Kingdom water;
    private Universe universe;

    @BeforeEach
    public void setUp() {
        lava = new Kingdom("DOLPHIN", "LAVA", new Ruler("King King", Gender.MALE));
        water = new Kingdom("WHALE", "WATER", new Ruler("Queen Mermaid", Gender.FEMALE));

        List<Kingdom> kingdoms = Arrays.asList(lava, water);

        this.universe = new Universe(kingdoms, 1);

    }

    @Test
    public void playMessages_ValidAllyRequestsFromLava_LavaBecomesRuler() throws NoSuchAlgorithmException {
        String messsage = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.water.emblem.length())
                                 .encrypt(this.water.emblem);
        Map.Entry<String, String> messageEntry = new AbstractMap.SimpleImmutableEntry<>(this.water.name, messsage);

        this.universe.executeMessages(Arrays.asList(messageEntry), this.lava.name);
        assertEquals(this.lava.name, this.universe.getRulingKingdom()
                                                  .get());
    }

    @Test
    public void playMessages_InvalidAllyRequestsFromLava_NoRuler() throws NoSuchAlgorithmException {
        String messsage = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.water.emblem.length())
                                 .encrypt("INVALID");
        Map.Entry<String, String> messageEntry = new AbstractMap.SimpleImmutableEntry<>(this.water.name, messsage);

        this.universe.executeMessages(Arrays.asList(messageEntry), this.lava.name);

        assertFalse(this.universe.getRulingKingdom()
                                 .isPresent());
    }

    @Test
    public void getRulingKingdomAllies_ValidAllyRequestsFromLava_KingdomsAllied() throws NoSuchAlgorithmException {
        String messsage = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.water.emblem.length())
                                 .encrypt(this.water.emblem);
        Map.Entry<String, String> messageEntry = new AbstractMap.SimpleImmutableEntry<>(this.water.name, messsage);

        this.universe.executeMessages(Arrays.asList(messageEntry), this.lava.name);
        assertTrue(this.universe.getRulingKingdomAllies()
                                .get()
                                .contains(this.water.name));
    }


}