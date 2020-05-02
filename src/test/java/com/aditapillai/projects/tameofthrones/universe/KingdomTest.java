package com.aditapillai.projects.tameofthrones.universe;

import com.aditapillai.projects.tameofthrones.cipher.Cipher;
import com.aditapillai.projects.tameofthrones.cipher.Ciphers;
import com.aditapillai.projects.tameofthrones.models.Gender;
import com.aditapillai.projects.tameofthrones.models.Message;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import com.aditapillai.projects.tameofthrones.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


class KingdomTest {

    private Kingdom lava;
    private Kingdom water;
    private PostService postService;

    @BeforeEach
    public void setUp() {
        lava = new Kingdom("DOLPHIN", "LAVA", new Ruler("King King", Gender.MALE));
        water = new Kingdom("WHALE", "WATER", new Ruler("Queen Mermaid", Gender.FEMALE));

        List<Kingdom> kingdoms = Arrays.asList(lava, water);
        this.postService = new PostService(kingdoms);
        this.lava.setPostService(this.postService);
        this.water.setPostService(this.postService);
    }

    @Test
    public void sendMessage_ValidAllyRequest_Allied() throws NoSuchAlgorithmException {
        Cipher seasarCipher = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.water.emblem.length());
        String encryptedMessage = seasarCipher.encrypt("ALEWHLELEL");
        this.lava.sendMessage("WATER", encryptedMessage);
        assertTrue(this.lava.getAllies()
                            .contains(this.water.name));
    }

    @Test
    public void sendMessage_InvalidAllyRequest_NotAllied() throws NoSuchAlgorithmException {
        Cipher seasarCipher = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.water.emblem.length());
        String encryptedMessage = seasarCipher.encrypt("INVALID");
        this.lava.sendMessage("WATER", encryptedMessage);
        assertFalse(this.lava.getAllies()
                             .contains(this.water.name));
    }

    @Test
    public void allyRequest_ValidAllyRequest_RespondYes() throws NoSuchAlgorithmException {
        Cipher seasarCipher = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.water.emblem.length());
        String encryptedMessage = seasarCipher.encrypt("ALEWHLELEL");

        Message allyRequestResponse = this.water.allyRequest(new Message(this.lava.name, this.water.name, encryptedMessage));
        assertEquals("YES", seasarCipher.decrypt(allyRequestResponse.body));
    }

    @Test
    public void allyRequest_ValidAllyRequest_RespondNo() throws NoSuchAlgorithmException {
        Cipher seasarCipher = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.water.emblem.length());
        String encryptedMessage = seasarCipher.encrypt("INVALID");

        Message allyRequestResponse = this.water.allyRequest(new Message(this.lava.name, this.water.name, encryptedMessage));
        assertEquals("NO", seasarCipher.decrypt(allyRequestResponse.body));
    }

    @Test
    public void equals_EqualKingdoms_TrueReturned() {
        Kingdom newKingdom = new Kingdom(this.lava.emblem, "random name", new Ruler("Ruling King", Gender.valueOf('M')));
        assertTrue(this.lava.equals(newKingdom));
    }

    @Test
    public void equals_KingdomsWithDifferentEmblem_FalseReturned() {
        Kingdom newKingdom = new Kingdom("PEAR", this.lava.name, new Ruler("Ruling King", Gender.valueOf('M')));
        assertFalse(this.lava.equals(newKingdom));
    }

    @Test
    public void hashCode_kingdom_hashcodeOfEmblemReturned() {
        assertEquals(Objects.hash(this.lava.emblem), this.lava.hashCode());
    }
}