package com.aditapillai.projects.tameofthrones.cipher;


class SeasarCipher implements Cipher {
    private final CharacterWheel charWheel;
    private final int key;

    public SeasarCipher(int key, CharacterWheel characterWheel) {
        this.key = key;
        this.charWheel = characterWheel;
    }

    @Override
    public String encrypt(String data) {
        return data.chars()
                   .mapToObj(character -> (char) character)
                   .map(character -> charWheel.moveClockWise(character, this.key))
                   .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                   .toString();
    }

    @Override
    public String decrypt(String data) {
        return data.chars()
                   .mapToObj(character -> (char) character)
                   .map(character -> charWheel.moveAntiClockwise(character, this.key))
                   .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                   .toString();

    }
}
