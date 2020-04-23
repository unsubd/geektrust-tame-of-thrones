package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.constraints.ErrorMessages;
import com.aditapillai.projects.tameofthrones.constraints.NotNull;
import com.aditapillai.projects.tameofthrones.models.Message;
import com.aditapillai.projects.tameofthrones.universe.Kingdom;

import java.util.Collection;
import java.util.Objects;

public class PostService {
    private final AddressRegistry addressRegistry;

    public PostService(@NotNull Collection<Kingdom> kingdoms) {
        Objects.requireNonNull(kingdoms, ErrorMessages.KINGDOMS_NOT_NULL_ERROR_MESSAGE);
        this.addressRegistry = new AddressRegistry(kingdoms);
    }

    public Message exchange(Message message) {
        Kingdom to = this.addressRegistry.getKingdomFromAddress(message.to);
        return to.allyRequest(message);
    }

    public String getEmblemFor(String name) {
        return this.addressRegistry.getKingdomFromAddress(name).emblem;
    }

    public boolean addKingdom(Kingdom kingdom) {
        return this.addressRegistry.addKingdom(kingdom);
    }

}
