package com.aditapillai.projects.tameofthrones.cipher;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharacterWheelTest {

    @Test
    void moveClockwiseTest() {
        CharacterWheel instance = CharacterWheel.getInstance();
        Assertions.assertEquals('D', instance.moveClockWise('A', 3));
        Assertions.assertEquals('C', instance.moveClockWise('Z', 3));
    }


    @Test
    void moveAntiClockwiseTest() {
        CharacterWheel instance = CharacterWheel.getInstance();
        Assertions.assertEquals('Z', instance.moveAntiClockwise('A', 1));
        Assertions.assertEquals('Y', instance.moveAntiClockwise('Z', 1));
    }
}