package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.universe.Kingdom;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

class AddressRegistry {
    private final Map<String, Kingdom> registry;

    AddressRegistry(Collection<Kingdom> kingdoms) {
        this.registry = kingdoms.stream()
                                .collect(Collectors.toMap(kingdom -> kingdom.name, Function.identity()));
    }

    Optional<Kingdom> getKingdomFromName(String address) {
        return Optional.ofNullable(this.registry.get(address));
    }

    public boolean addKingdom(Kingdom kingdom) {
        boolean present = this.registry.containsKey(kingdom.name);
        boolean added = false;
        if (!present) {
            this.registry.put(kingdom.name, kingdom);
            added = true;
        }
        return added;
    }
}
