package com.aditapillai.projects.tameofthrones.universe;

import com.aditapillai.projects.tameofthrones.cipher.Cipher;
import com.aditapillai.projects.tameofthrones.cipher.Ciphers;
import com.aditapillai.projects.tameofthrones.computation.StringUtils;
import com.aditapillai.projects.tameofthrones.constraints.ErrorMessages;
import com.aditapillai.projects.tameofthrones.constraints.NotNull;
import com.aditapillai.projects.tameofthrones.models.Message;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import com.aditapillai.projects.tameofthrones.services.PostService;
import com.aditapillai.projects.tameofthrones.universe.constants.MessageResponses;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Kingdom {
    public final String name;
    public final String emblem;
    private final Set<String> allies;
    private Ruler ruler;
    private PostService postService;

    public Kingdom(@NotNull String emblem, @NotNull String name, @NotNull Ruler ruler) {
        Objects.requireNonNull(emblem, ErrorMessages.EMBLEM_NOT_NULL_ERROR_MESSAGE);
        Objects.requireNonNull(name, ErrorMessages.NAME_NOT_NULL_ERROR_MESSAGE);
        Objects.requireNonNull(ruler, ErrorMessages.RULER_NOT_NULL_ERROR_MESSAGE);

        this.emblem = emblem;
        this.name = name;
        this.ruler = ruler;
        this.allies = new LinkedHashSet<>();
    }

    public void sendMessage(String to, String body) {
        Message message = new Message(this.name, to, body);
        Message response = this.postService.exchange(message);
        if (this.hasOtherKingdomAllied(response)) {
            this.allies.add(response.from);
        }
    }

    private boolean hasOtherKingdomAllied(Message message) {
        try {
            Cipher cipher = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.postService.getEmblemFor(message.from)
                                                                                       .length());
            String decryptedMessage = cipher.decrypt(message.body);
            return MessageResponses.POSITIVE_RESPONSE.equalsIgnoreCase(decryptedMessage);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    public Message allyRequest(Message message) {
        Message response;
        try {
            Cipher cipher = Ciphers.cipher(Ciphers.SEASAR_CIPHER_TYPE, this.emblem.length());
            String decryptedMessage = cipher.decrypt(message.body);
            String shouldWeAlly = this.shouldWeAlly(decryptedMessage) ? MessageResponses.POSITIVE_RESPONSE:
                    MessageResponses.NEGATIVE_RESPONSE;
            String responseBody = cipher.encrypt(shouldWeAlly);
            response = new Message(this.name, message.from, responseBody);
        } catch (NoSuchAlgorithmException e) {
            return new Message(this.name, message.from, MessageResponses.NEGATIVE_RESPONSE);
        }

        return response;
    }

    private boolean shouldWeAlly(String message) {
        return StringUtils.textContainsAllCharactersOfWord(message, this.emblem);
    }

    void setPostService(@NotNull PostService postService) {
        Objects.requireNonNull(postService);
        this.postService = postService;
    }

    Set<String> getAllies() {
        return new LinkedHashSet<>(this.allies);
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
