package com.aditapillai.projects.tameofthrones.models;

import com.aditapillai.projects.tameofthrones.constraints.NotNull;

import java.util.Objects;

public class Kingdom {
    public final String name;
    public final String emblem;
    private Ruler ruler;

    public Kingdom(@NotNull String emblem, @NotNull String name) {
        Objects.requireNonNull(emblem, "Emblem for a kingdom cannot be null");
        Objects.requireNonNull(name, "Name of a kingdom cannot be null");
        this.emblem = emblem;
        this.name = name;
    }

    public Ruler getRuler() {
        return ruler;
    }

    public void setRuler(@NotNull Ruler ruler) {
        Objects.requireNonNull(ruler);
        this.ruler = ruler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kingdom kingdom = (Kingdom) o;
        return emblem.equals(kingdom.emblem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emblem);
    }
}
