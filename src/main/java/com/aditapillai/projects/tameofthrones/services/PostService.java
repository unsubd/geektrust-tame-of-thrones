package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.models.Kingdom;
import com.aditapillai.projects.tameofthrones.models.Message;

public class PostService {
    private AddressRegistry addressRegistry;

    public PostService(AddressRegistry addressRegistry) {
        this.addressRegistry = addressRegistry;
    }

    public Message exchange(Message message) {
        Kingdom to = this.addressRegistry.getKingdomFromAddress(message.to);
        return to.exchangeMessage(message);
    }
}
