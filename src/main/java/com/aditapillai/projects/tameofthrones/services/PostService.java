package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.constraints.NotNull;
import com.aditapillai.projects.tameofthrones.error.ErrorMessages;
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

    public Message sendMessage(Message message) {
        Kingdom to = this.addressRegistry.getKingdomFromName(message.to)
                                         .orElseThrow(() -> new RuntimeException(
                                                 String.format(ErrorMessages.KINGDOM_NOT_FOUND_ERROR_MESSAGE_FORMAT, message.to)));
        return to.allyRequest(message);
    }

    public String getEmblemFor(String name) {
        return this.addressRegistry.getKingdomFromName(name)
                                   .map(kingdom -> kingdom.emblem)
                                   .orElseThrow(() -> new RuntimeException(
                                           String.format(ErrorMessages.KINGDOM_NOT_FOUND_ERROR_MESSAGE_FORMAT, name)));
    }

    public boolean registerKingdom(Kingdom kingdom) {
        return this.addressRegistry.addKingdom(kingdom);
    }

}
