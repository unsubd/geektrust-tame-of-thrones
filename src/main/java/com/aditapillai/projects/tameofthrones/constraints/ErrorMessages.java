package com.aditapillai.projects.tameofthrones.constraints;

public interface ErrorMessages {
    String EMBLEM_NOT_NULL_ERROR_MESSAGE = "Emblem for a kingdom cannot be null";
    String NAME_NOT_NULL_ERROR_MESSAGE = "Name of a kingdom cannot be null";
    String RULER_NOT_NULL_ERROR_MESSAGE = "Ruler of a kingdom cannot be null";
    String KINGDOM_NOT_FOUND_ERROR_MESSAGE_FORMAT = "Kingdom with name %s not found";
    String KINGDOMS_NOT_NULL_ERROR_MESSAGE = "Kingdoms should not be null";
}
