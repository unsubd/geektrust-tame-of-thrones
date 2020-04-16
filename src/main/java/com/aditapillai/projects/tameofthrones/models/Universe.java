package com.aditapillai.projects.tameofthrones.models;

import com.aditapillai.projects.tameofthrones.constraints.NotNull;
import com.aditapillai.projects.tameofthrones.utils.IOUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Universe {
    private static Universe universe;
    private final Set<Kingdom> kingdoms;
    private Kingdom rulingKingdom;

    private Universe(@NotNull Set<Kingdom> kingdoms) {
        Objects.requireNonNull(kingdoms);
        this.kingdoms = kingdoms;
    }

    public static Universe getInstance() {
        if (universe == null) {
            synchronized (Universe.class) {
                Universe.universe = new Universe(new HashSet<>(IOUtils.getAllKingdoms()));
            }
        }
        return universe;
    }

    public Kingdom getRulingKingdom() {
        return this.rulingKingdom;
    }

    public void setRulingKingdom(@NotNull Kingdom kingdom) {
        Objects.requireNonNull(kingdom, "The ruling kingdom cannot be set to a null value");
        this.rulingKingdom = kingdom;
    }

    public Set<Kingdom> getKingdoms() {
        return new HashSet<>(this.kingdoms);
    }

}
