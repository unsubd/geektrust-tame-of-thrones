package com.aditapillai.projects.tameofthrones.cipher;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CiphersTest {

    @Test
    void cipher() throws NoSuchAlgorithmException {
        Cipher cipher = Ciphers.cipher("seasar", 1);
        assertNotNull(cipher);
    }

    @Test
    void cipherNegative() {
        boolean result = false;
        try {
            Ciphers.cipher("invalid", 1);
        } catch (NoSuchAlgorithmException e) {
            result = true;
        }
        assertTrue(result);
    }
}