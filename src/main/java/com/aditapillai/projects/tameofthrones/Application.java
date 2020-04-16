package com.aditapillai.projects.tameofthrones;

import com.aditapillai.projects.tameofthrones.utils.IOUtils;

public class Application {
    public static void main(String[] args) {
        IOUtils.getAllKingdoms()
               .forEach(kingdom -> System.out.println(String.format("%s,%s,%s", kingdom.name,
                       kingdom.emblem, kingdom.getRuler().name)));
    }
}
