package com.aditapillai.projects.tameofthrones.cipher;


class SeasarCipher implements Cipher {
    private static final CharacterWheel CHAR_WHEEL = CharacterWheel.getInstance();
    private final int key;

    public SeasarCipher(int key) {
        this.key = key;
    }

    @Override
    public String encrypt(String data) {
        return data.chars()
                   .mapToObj(character -> (char) character)
                   .map(character -> CHAR_WHEEL.moveClockWise(character, this.key))
                   .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                   .toString();
    }

    @Override
    public String decrypt(String data) {
        return data.chars()
                   .mapToObj(character -> (char) character)
                   .map(character -> CHAR_WHEEL.moveAntiClockwise(character, this.key))
                   .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                   .toString();

    }
}
