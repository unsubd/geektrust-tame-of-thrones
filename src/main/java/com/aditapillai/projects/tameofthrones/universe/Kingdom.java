package com.aditapillai.projects.tameofthrones.universe;

import com.aditapillai.projects.tameofthrones.cipher.Cipher;
import com.aditapillai.projects.tameofthrones.cipher.Ciphers;
import com.aditapillai.projects.tameofthrones.constraints.NotNull;
import com.aditapillai.projects.tameofthrones.models.Message;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import com.aditapillai.projects.tameofthrones.services.PostService;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Kingdom {
    public final String name;
    public final String emblem;
    private final List<String> allies;
    private Ruler ruler;
    private PostService postService;

    public Kingdom(@NotNull String emblem, @NotNull String name) {
        Objects.requireNonNull(emblem, "Emblem for a kingdom cannot be null");
        Objects.requireNonNull(name, "Name of a kingdom cannot be null");
        this.emblem = emblem;
        this.name = name;
        this.allies = new LinkedList<>();
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

    public boolean hasAllied(Message message) {
        try {
            Cipher cipher = Ciphers.cipher("seasar", this.postService.getEmblemFor(message.from)
                                                                     .length());
            String decryptedMessage = cipher.decrypt(message.body);
            return "YES".equalsIgnoreCase(decryptedMessage);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    public void sendMessage(String to, String body) {
        Message message = new Message(this.name, to, body);
        Message response = this.postService.exchange(message);
        if (this.hasAllied(response)) {
            this.allies.add(response.from);
        }
    }

    public Message inbox(Message message) {
        Message response;
        try {
            Cipher cipher = Ciphers.cipher("seasar", this.emblem.length());
            String decryptedMessage = cipher.decrypt(message.body);
            String shouldWeAlly = this.shouldWeAlly(decryptedMessage) ? "YES": "NO";
            String responseBody = cipher.encrypt(shouldWeAlly);
            response = new Message(this.name, message.from, responseBody);
        } catch (NoSuchAlgorithmException e) {
            return new Message(this.name, message.from, "NO");
        }

        return response;
    }

    private boolean shouldWeAlly(String message) {
        Map<Character, Long> charCountMap =
                message.chars()
                       .mapToObj(character -> (char) character)
                       .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        boolean result = true;

        for (char character : emblem.toCharArray()) {
            Long count = charCountMap.getOrDefault(character, 0L);
            if (count > 0) {
                charCountMap.put(character, --count);
            } else {
                result = false;
                break;
            }
        }

        return result;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public List<String> getAllies() {
        return Collections.unmodifiableList(this.allies);
    }
}
