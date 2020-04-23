package com.aditapillai.projects.tameofthrones;

import com.aditapillai.projects.tameofthrones.universe.Universe;
import com.aditapillai.projects.tameofthrones.utils.IOUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) throws IOException {

        Universe universe = new Universe(IOUtils.getAllKingdoms(), 3);

        List<Map.Entry<String, String>> parsedInput = Files.lines(Paths.get(args[0]))
                                                           .map(line -> line.replaceAll("", "")
                                                                            .split("\\s"))
                                                           .map(split -> new AbstractMap.SimpleImmutableEntry<>(split[0],
                                                                   String.join("", Arrays.copyOfRange(split, 1, split.length))))
                                                           .collect(Collectors.toList());

        String from = "SPACE";
        universe.playMessages(parsedInput, from);

        System.out.println(universe.getRulingKingdomAllies()
                                   .filter(allies -> !allies.isEmpty())
                                   .map(allies -> allies.stream()
                                                        .collect(() -> new StringBuilder(from),
                                                                (acc, current) -> {
                                                                    acc.append(" ");
                                                                    acc.append(current);
                                                                }, StringBuilder::append))
                                   .map(StringBuilder::toString)
                                   .orElse("NONE"));
    }
}
