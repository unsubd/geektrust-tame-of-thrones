package com.aditapillai.projects.tameofthrones.cipher;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharWheelTest {

    @Test
    void addTest() {
        CharWheel instance = CharWheel.getInstance();
        Assertions.assertEquals('D', instance.add('A', 3));
        Assertions.assertEquals('C', instance.add('Z', 3));
    }


    @Test
    void subtract() {
        CharWheel instance = CharWheel.getInstance();
        Assertions.assertEquals('Z', instance.subtract('A', 1));
        Assertions.assertEquals('Y', instance.subtract('Z', 1));
    }
}