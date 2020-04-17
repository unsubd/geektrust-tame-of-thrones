package com.aditapillai.projects.tameofthrones.services.utils;

import com.aditapillai.projects.tameofthrones.models.Gender;
import com.aditapillai.projects.tameofthrones.models.Ruler;
import com.aditapillai.projects.tameofthrones.services.PostService;
import com.aditapillai.projects.tameofthrones.universe.Kingdom;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IOUtils {

    public static List<Kingdom> getAllKingdoms() {
        InputStream in = IOUtils.class.getClassLoader()
                                      .getResourceAsStream("init/rulers.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Map<String, Ruler> kingdomRulerMap = reader.lines()
                                                   .map(line -> line.split(","))
                                                   .collect(HashMap::new, (map, lineSplit) -> {
                                                       Ruler ruler = new Ruler(lineSplit[0],
                                                               Gender.valueOf(lineSplit[1].charAt(0)));
                                                       String kingdom = lineSplit[2];
                                                       map.put(kingdom, ruler);
                                                   }, HashMap::putAll);

        in = IOUtils.class.getClassLoader()
                          .getResourceAsStream("init/kingdoms.txt");

        reader = new BufferedReader(new InputStreamReader(in));
        List<Kingdom> kingdoms = reader.lines()
                                       .map(line -> line.split(","))
                                       .map(split -> new Kingdom(split[1], split[0]))
                                       .peek(kingdom -> kingdom.setRuler(kingdomRulerMap.get(kingdom.name)))
                                       .collect(Collectors.toList());
        PostService postService = new PostService(kingdoms);
        kingdoms.forEach(kingdom -> kingdom.setPostService(postService));
        return kingdoms;

    }
}
