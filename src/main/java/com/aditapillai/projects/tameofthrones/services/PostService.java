package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.constraints.NotNull;
import com.aditapillai.projects.tameofthrones.models.Message;
import com.aditapillai.projects.tameofthrones.universe.Kingdom;

import java.util.Collection;
import java.util.Objects;

public class PostService {
    private final AddressRegistry addressRegistry;

    public PostService(@NotNull Collection<Kingdom> kingdoms) {
        Objects.requireNonNull(kingdoms, "Kingdoms should not be null");
        this.addressRegistry = new AddressRegistry(kingdoms);
    }

    public Message exchange(Message message) {
        Kingdom to = this.addressRegistry.getKingdomFromAddress(message.to);
        return to.inbox(message);
    }

    public String getEmblemFor(String name) {
        return this.addressRegistry.getKingdomFromAddress(name).emblem;
    }

}
