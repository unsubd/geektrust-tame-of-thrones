package com.aditapillai.projects.tameofthrones.services;

import com.aditapillai.projects.tameofthrones.models.Kingdom;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class AddressRegistry {
    private final Map<String, Kingdom> registry;

    AddressRegistry(Collection<Kingdom> kingdoms) {
        this.registry = kingdoms.stream()
                                .collect(Collectors.toMap(kingdom -> kingdom.name, Function.identity()));
    }

    Kingdom getKingdomFromAddress(String address) {
        return this.registry.get(address);
    }

}
