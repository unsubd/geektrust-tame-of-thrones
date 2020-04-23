package com.aditapillai.projects.tameofthrones.cipher;

import java.security.NoSuchAlgorithmException;

public class Ciphers {
    public static final String SEASAR_CIPHER_TYPE = "seasar";
    private static final CharacterWheel CHARACTER_WHEEL = new CharacterWheel('A', 'Z');

    public static Cipher cipher(String scheme, int key) throws NoSuchAlgorithmException {
        switch (scheme) {
            case SEASAR_CIPHER_TYPE:
                return new SeasarCipher(key, CHARACTER_WHEEL);
            default:
                throw new NoSuchAlgorithmException(String.format("%s Is not a valid cipher", scheme));
        }
    }
}
