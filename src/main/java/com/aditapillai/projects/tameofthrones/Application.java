package com.aditapillai.projects.tameofthrones;

import com.aditapillai.projects.tameofthrones.models.Pair;
import com.aditapillai.projects.tameofthrones.universe.Kingdom;
import com.aditapillai.projects.tameofthrones.universe.Universe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {
    public static void main(String[] args) throws IOException {
        Universe universe = Universe.getInstance();
        Kingdom from = universe.getKingdom("SPACE");

        Files.lines(Paths.get(args[0]))
             .map(line -> line.split("\\s"))
             .map(split -> new Pair<>(split[0], split[1]))
             .forEach(pair -> from.sendMessage(pair.FIRST, pair.SECOND));

        if (from.getAllies()
                .isEmpty()) {
            System.out.println("NONE");
        } else {
            StringBuilder result = new StringBuilder(from.name);
            from.getAllies()
                .forEach(ally -> result.append(" ")
                                       .append(ally));
            System.out.println(result.toString());
        }

        if (from.getAllies()
                .size() >= 3) {
            universe.setRulingKingdom(from);
        }
    }
}
