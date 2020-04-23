package com.aditapillai.projects.tameofthrones.cipher;


class CharacterWheel {
    private final Node first;

    CharacterWheel(char lowerBound, char upperBound) {
        this.first = new Node(lowerBound);
        Node node = this.first;
        for (char i = (char) (lowerBound + 1); i <= upperBound; i++) {
            Node next = new Node(i);
            next.prev = node;
            node.next = next;
            node = node.next;
        }
        node.next = this.first;
        this.first.prev = node;
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
