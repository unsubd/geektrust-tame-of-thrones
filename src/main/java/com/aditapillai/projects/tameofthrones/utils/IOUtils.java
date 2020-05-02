package com.aditapillai.projects.tameofthrones.utils;

import com.aditapillai.projects.tameofthrones.models.Gender;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import com.aditapillai.projects.tameofthrones.universe.Kingdom;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IOUtils {

    private static final String RULERS_INPUT_PATH = "init/rulers.txt";
    private static final String KINGDOMS_INPUT_PATH = "init/kingdoms.txt";

    public static List<Kingdom> readInputAndParseKingdoms() {
        InputStream in = IOUtils.class.getClassLoader()
                                      .getResourceAsStream(RULERS_INPUT_PATH);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Map<String, Ruler> kingdomRulerMap = reader.lines()
                                                   .map(line -> line.split(","))
                                                   .collect(HashMap::new, (map, lineSplit) -> {
                                                       Ruler ruler = new Ruler(lineSplit[0], Gender.valueOf(lineSplit[1].charAt(0)));
                                                       String kingdom = lineSplit[2];
                                                       map.put(kingdom, ruler);
                                                   }, HashMap::putAll);

        in = IOUtils.class.getClassLoader()
                          .getResourceAsStream(KINGDOMS_INPUT_PATH);

        reader = new BufferedReader(new InputStreamReader(in));

        return reader.lines()
                     .map(line -> line.split(","))
                     .map(split -> new Kingdom(split[1], split[0], kingdomRulerMap.get(split[0])))
                     .collect(Collectors.toList());
    }
}
