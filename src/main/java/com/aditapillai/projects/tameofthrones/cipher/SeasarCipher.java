package com.aditapillai.projects.tameofthrones.cipher;


class SeasarCipher implements Cipher {
    private static final CharacterWheel CHAR_WHEEL = CharacterWheel.getInstance();
    private final int key;

    public SeasarCipher(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String data) {
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = CHAR_WHEEL.moveClockWise(chars[i], key);
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String data) {
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = CHAR_WHEEL.moveAntiClockwise(chars[i], key);
        }
        return new String(chars);
    }
}
