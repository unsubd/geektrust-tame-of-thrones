package com.aditapillai.projects.tameofthrones.cipher;


import com.aditapillai.projects.tameofthrones.cipher.constants.CipherConstants;

class CharacterWheel {
    private static CharacterWheel characterWheel;
    private final Node first;

    private CharacterWheel(int lower, int upper) {
        this.first = new Node((char) lower);
        Node node = this.first;
        for (int i = lower + 1; i <= upper; i++) {
            Node next = new Node((char) i);
            next.prev = node;
            node.next = next;
            node = node.next;
        }
        node.next = this.first;
        this.first.prev = node;
    }

    static CharacterWheel getInstance() {
        if (characterWheel == null) {
            synchronized (CharacterWheel.class) {
                characterWheel = new CharacterWheel(CipherConstants.CHARACTER_LOWER_BOUND, CipherConstants.CHARACTER_UPPER_BOUND);
            }
        }
        return characterWheel;
    }

    char moveClockWise(char character, int increment) {
        Node node = this.first;
        while (node.data != character) {
            node = node.next;
        }

        while (increment-- > 0) {
            node = node.next;
        }

        return node.data;

    }

    char moveAntiClockwise(char character, int decrement) {
        Node node = this.first;
        while (node.data != character) {
            node = node.next;
        }

        while (decrement-- > 0) {
            node = node.prev;
        }
        return node.data;
    }

    private static class Node {
        final char data;
        Node next;
        Node prev;

        Node(char data) {
            this.data = data;
        }
    }
}
