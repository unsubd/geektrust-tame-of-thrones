package com.aditapillai.projects.tameofthrones.cipher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

class CiphersTest {

    @Test
    public void ciphers_SeasarCipher_returned() throws NoSuchAlgorithmException {
        Cipher seasar = Ciphers.cipher("seasar", 10);
        Assertions.assertEquals(SeasarCipher.class, seasar.getClass());
    }

    @Test
    public void ciphers_InvalidCipher_ExceptionThrown() {
        boolean result = false;
        try {
            Ciphers.cipher("invalid", 10);
        } catch (NoSuchAlgorithmException exception) {
            result = true;
        }
        Assertions.assertTrue(true, "Exception not thrown as expected");
    }

}