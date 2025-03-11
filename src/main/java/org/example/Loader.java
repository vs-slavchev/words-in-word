package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Loader {
    private static final Logger logger = Logger.getLogger(Loader.class.getName());

    public static List<String> loadAllWords() {
        URL wordsUrl;
        try {
            wordsUrl = new URL("https://raw.githubusercontent" +
                    ".com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        } catch (IOException e) {
            logger.severe("Failed to create URL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(wordsUrl.openStream()))) {
            return reader.lines().skip(2).collect(Collectors.toList());
        } catch (IOException e) {
            logger.severe("Failed to load words from URL: " + e.getMessage());
            e.printStackTrace();
        }
        return List.of();
    }
}
