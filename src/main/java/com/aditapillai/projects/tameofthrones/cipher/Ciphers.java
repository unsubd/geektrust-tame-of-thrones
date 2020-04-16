package com.aditapillai.projects.tameofthrones.cipher;

import java.security.NoSuchAlgorithmException;

public class Ciphers {
    public static Cipher cipher(String scheme, int key) throws NoSuchAlgorithmException {
        switch (scheme) {
            case "seasar":
                return new SeasarCipher(key);
            default:
                throw new NoSuchAlgorithmException(String.format("%s Is not a valid cipher", scheme));
        }
    }
}
