package com.aditapillai.projects.tameofthrones.cipher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharacterWheelTest {

    private final CharacterWheel characterWheel = new CharacterWheel('A', 'Z');

    @Test
    public void moveClockWiseBy3_StartFromA_ReturnD() {
        Assertions.assertEquals('D', this.characterWheel.moveClockWise('A', 3));
    }

    @Test
    public void moveClockWiseBy26_StartFromA_ReturnA() {
        Assertions.assertEquals('A', this.characterWheel.moveClockWise('A', 26));
    }

    @Test
    public void moveAntiClockWiseBy3_StartFromA_ReturnD() {
        Assertions.assertEquals('A', this.characterWheel.moveAntiClockwise('D', 3));
    }

    @Test
    public void moveAntiClockWiseBy26_StartFromA_ReturnA() {
        Assertions.assertEquals('A', this.characterWheel.moveAntiClockwise('A', 26));
    }


}