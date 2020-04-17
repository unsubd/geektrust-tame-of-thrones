package com.aditapillai.projects.tameofthrones.universe;

import com.aditapillai.projects.tameofthrones.cipher.Cipher;
import com.aditapillai.projects.tameofthrones.cipher.Ciphers;
import com.aditapillai.projects.tameofthrones.models.Gender;
import com.aditapillai.projects.tameofthrones.models.Message;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import com.aditapillai.projects.tameofthrones.services.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Set;

class KingdomTest {

    Kingdom land;
    Kingdom air;
    PostService postService;

    @BeforeEach
    public void setUp() {
        this.land = new Kingdom("PANDA", "LAND");
        this.air = new Kingdom("OWL", "AIR");
        this.land.setRuler(new Ruler("King 1", Gender.MALE));
        this.air.setRuler(new Ruler("Queen 2", Gender.FEMALE));
        this.postService = new PostService(Arrays.asList(this.land, air));
        air.setPostService(this.postService);
        this.land.setPostService(postService);
    }

    @Test
    void sendMessage() throws NoSuchAlgorithmException {
        Cipher cipher = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, "OWL".length());
        String encryptedBody = cipher.encrypt("LOLOW");
        land.sendMessage("AIR", encryptedBody);
        Assertions.assertEquals(1, land.getAllies()
                                       .size());
        Assertions.assertTrue(this.land.getAllies()
                                       .contains(this.air.name));
    }

    @Test
    void allyRequest() throws NoSuchAlgorithmException {
        Cipher cipher = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, "PANDA".length());
        String message = cipher.encrypt("ALLYMEPANDA");
        Message response = this.land.allyRequest(new Message(this.air.name, this.land.name, message));
        Assertions.assertEquals("YES", cipher.decrypt(response.body));
        Assertions.assertEquals(this.land.name, response.from);
        Assertions.assertEquals(this.air.name, response.to);
    }


    @Test
    void getAllies() {
        Set<String> allies = this.land.getAllies();
        allies.add("SPACE");
        Assertions.assertTrue(this.land.getAllies()
                                       .isEmpty());
    }

}