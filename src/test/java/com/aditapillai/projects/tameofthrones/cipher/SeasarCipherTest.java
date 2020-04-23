package com.aditapillai.projects.tameofthrones.cipher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SeasarCipherTest {
    private final SeasarCipher pandaCipher = new SeasarCipher("PANDA".length(), new CharacterWheel('A', 'Z'));
    private final SeasarCipher landCipher = new SeasarCipher("LAND".length(), new CharacterWheel('A', 'Z'));

    @Test
    public void encrypt_secretWithPandaCipher_encrypted() {
        Assertions.assertEquals("XJHWJY", this.pandaCipher.encrypt("SECRET"));
    }

    @Test
    public void encrypt_secretWithLandCipher_encrypted() {
        Assertions.assertEquals("WIGVIX", this.landCipher.encrypt("SECRET"));
    }

    @Test
    public void decrypt_secretWithPandaCipher_decrypted() {
        Assertions.assertEquals("SECRET", this.pandaCipher.decrypt("XJHWJY"));
    }

    @Test
    public void decrypt_secretWithLandCipher_decrypted() {
        Assertions.assertEquals("SECRET", this.landCipher.decrypt("WIGVIX"));
    }
}