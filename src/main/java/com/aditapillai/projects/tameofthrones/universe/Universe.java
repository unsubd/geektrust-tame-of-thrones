package com.aditapillai.projects.tameofthrones.universe;

import com.aditapillai.projects.tameofthrones.constraints.NotNull;
import com.aditapillai.projects.tameofthrones.error.ErrorMessages;
import com.aditapillai.projects.tameofthrones.services.PostService;

import java.util.*;

public class Universe {
    private final Set<Kingdom> kingdoms;
    private final int minAlliesToBecomeRuler;
    private final PostService postService;
    private Kingdom rulingKingdom;

    public Universe(@NotNull Collection<Kingdom> kingdoms, int minAlliesToBecomeRuler) {
        Objects.requireNonNull(kingdoms);
        this.kingdoms = new HashSet<>(kingdoms);
        this.postService = new PostService(kingdoms);
        this.minAlliesToBecomeRuler = minAlliesToBecomeRuler;
        this.kingdoms.forEach(kingdom -> kingdom.setPostService(postService));
    }

    public void playMessages(List<Map.Entry<String, String>> messagesToKingdoms, String from) {
        Kingdom fromKingdom = this.kingdoms.stream()
                                           .filter(kingdom -> from.equals(kingdom.name))
                                           .findFirst()
                                           .orElseThrow(() -> new RuntimeException(String.format(ErrorMessages.KINGDOM_NOT_FOUND_ERROR_MESSAGE_FORMAT, from)));

        messagesToKingdoms.forEach(messageEntry -> fromKingdom.sendMessage(messageEntry.getKey(), messageEntry.getValue()));

        if (fromKingdom.getAllies()
                       .size() >= this.minAlliesToBecomeRuler) {
            this.rulingKingdom = fromKingdom;
        }
    }


    public Optional<Set<String>> getRulingKingdomAllies() {
        return Optional.ofNullable(this.rulingKingdom)
                       .map(Kingdom::getAllies);
    }

    public Optional<String> getRulingKingdom() {
        return Optional.ofNullable(this.rulingKingdom)
                       .map(kingdom -> kingdom.name);
    }

}
