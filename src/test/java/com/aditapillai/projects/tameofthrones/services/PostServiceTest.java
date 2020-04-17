package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.cipher.Cipher;
import com.aditapillai.projects.tameofthrones.cipher.Ciphers;
import com.aditapillai.projects.tameofthrones.models.Message;
import com.aditapillai.projects.tameofthrones.universe.Universe;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest {
    Universe universe = Universe.getInstance();

    @Test
    void exchange() throws NoSuchAlgorithmException {
        Cipher cipher = Ciphers.cipher("seasar", "OWL".length());
        PostService postService = new PostService(universe.getKingdoms());
        Message exchange = postService.exchange(new Message("LAND", "AIR", cipher.encrypt("OWL")));
        assertEquals("AIR", exchange.from);
        assertEquals("LAND", exchange.to);
    }

    @Test
    void getEmblemFor() {
        PostService postService = new PostService(universe.getKingdoms());
        assertEquals("PANDA", postService.getEmblemFor("LAND"));
    }
}