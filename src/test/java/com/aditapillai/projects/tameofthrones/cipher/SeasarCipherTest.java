package com.aditapillai.projects.tameofthrones.cipher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SeasarCipherTest {

    @Test
    void encrypt() {
        SeasarCipher seasarCipher = new SeasarCipher(3);
        String encryptedText = seasarCipher.encrypt("OLWL");
        Assertions.assertEquals("ROZO", encryptedText);

        encryptedText = seasarCipher.encrypt("YES");
        Assertions.assertEquals("BHV", encryptedText);
    }

    @Test
    void decrypt() {
        SeasarCipher seasarCipher = new SeasarCipher(3);
        String decryptedText = seasarCipher.decrypt("ROZO");
        Assertions.assertEquals("OLWL", decryptedText);

        Assertions.assertEquals("YES", seasarCipher.decrypt("BHV"));
    }
}