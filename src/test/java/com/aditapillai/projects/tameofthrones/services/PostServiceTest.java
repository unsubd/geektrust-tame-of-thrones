package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.models.Gender;
import com.aditapillai.projects.tameofthrones.models.Message;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import com.aditapillai.projects.tameofthrones.universe.Kingdom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    private PostService postService;
    private Kingdom lava;
    private Kingdom water;

    @BeforeEach
    public void setUp() {
        lava = new Kingdom("DOLPHIN", "LAVA", new Ruler("King King", Gender.MALE));
        water = new Kingdom("WHALE", "WATER", new Ruler("Queen Mermaid", Gender.FEMALE));

        List<Kingdom> kingdoms = Arrays.asList(lava);
        this.postService = new PostService(kingdoms);

    }

    @Test
    public void registerKingdom_NewKingdom_TrueReturned() {
        assertTrue(this.postService.registerKingdom(water));
    }

    @Test
    public void registerKingdom_ExistingKingdom_FalseReturned() {
        assertFalse(this.postService.registerKingdom(lava));
    }

    @Test
    public void getEmblem_ExistingKingdom_EmblemReturned() {
        assertEquals("DOLPHIN", this.postService.getEmblemFor("LAVA"));
    }

    @Test
    public void getEmblem_InvalidKingdom_ExceptionThrown() {
        boolean result = false;
        try {
            this.postService.getEmblemFor("invalid");
        } catch (RuntimeException exception) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    public void exchangeMessage_ValidMessage_ResponseReturned() {
        this.postService.registerKingdom(this.water);
        Message message = new Message(this.lava.name, this.water.name, "RANDOMMESSAGE");
        Message response = this.postService.sendMessage(message);
        assertEquals(this.water.name, response.from);
    }

    @Test
    public void exchangeMessage_InvalidMessage_ExceptionThrown() {
        Message message = new Message(this.lava.name, this.water.name, "RANDOMMESSAGE");
        boolean result = false;
        try {
            this.postService.sendMessage(message);
        } catch (RuntimeException exception) {
            result = true;
        }

        assertTrue(result);
    }

}