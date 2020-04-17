package com.aditapillai.projects.tameofthrones.cipher;


class CharWheel {
    private static CharWheel charWheel;
    private final Node first;

    private CharWheel(int lower, int upper) {
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

    static CharWheel getInstance() {
        if (charWheel == null) {
            synchronized (CharWheel.class) {
                charWheel = new CharWheel(65, 90);
            }
        }
        return charWheel;
    }

    char add(char character, int increment) {
        Node node = this.first;
        while (node.data != character) {
            node = node.next;
        }

        while (increment-- > 0) {
            node = node.next;
        }

        return node.data;

    }

    char subtract(char character, int decrement) {
        Node node = this.first;
        while (node.data != character) {
            node = node.next;
        }

        while (decrement-- > 0) {
            node = node.prev;
        }
        return node.data;
    }

    private class Node {
        final char data;
        Node next;
        Node prev;

        Node(char data) {
            this.data = data;
        }
    }
}
