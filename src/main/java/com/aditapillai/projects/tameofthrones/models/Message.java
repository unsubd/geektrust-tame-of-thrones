package com.aditapillai.projects.tameofthrones.models;

public class Message {
    public final String from;
    public final String to;
    public final String body;

    public Message(String from, String to, String body) {
        this.from = from;
        this.to = to;
        this.body = body;
    }
}
