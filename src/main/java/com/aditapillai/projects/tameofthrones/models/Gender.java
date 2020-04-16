package com.aditapillai.projects.tameofthrones.models;

public enum Gender {
    MALE, FEMALE, OTHER;

    public static Gender valueOf(char gender) {
        switch (gender) {
            case 'm':
            case 'M':
                return MALE;
            case 'f':
            case 'F':
                return FEMALE;
            default:
                return OTHER;
        }
    }
}
