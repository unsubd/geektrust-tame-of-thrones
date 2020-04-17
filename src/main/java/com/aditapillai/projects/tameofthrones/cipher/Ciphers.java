package com.aditapillai.projects.tameofthrones.cipher;

import java.security.NoSuchAlgorithmException;

public class Ciphers {
    public static final String SEASAR_CIPHER_TYPE = "seasar";

    public static Cipher cipher(String scheme, int key) throws NoSuchAlgorithmException {
        switch (scheme) {
            case SEASAR_CIPHER_TYPE:
                return new SeasarCipher(key);
            default:
                throw new NoSuchAlgorithmException(String.format("%s Is not a valid cipher", scheme));
        }
    }
}
