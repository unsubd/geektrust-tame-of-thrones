package com.aditapillai.projects.tameofthrones.cipher;


class SeasarCipher implements Cipher {
    private static final CharWheel CHAR_WHEEL = CharWheel.getInstance();
    private final int key;

    public SeasarCipher(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String data) {
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = CHAR_WHEEL.add(chars[i], key);
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String data) {
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = CHAR_WHEEL.subtract(chars[i], key);
        }
        return new String(chars);
    }
}
