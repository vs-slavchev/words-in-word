package org.example;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        List<String> allWords = Loader.loadAllWords();
        allWords.add("A"); // not scrabble words, but valid 1-letter ones
        allWords.add("I");
        logger.info("Loaded " + allWords.size() + " words total");
        Instant start = Instant.now();

        Trie trie = new Trie();
        allWords.forEach(trie::insert);

        List<String> nineLetterWordsWithAorI =
                allWords.stream().filter(Main::isValid).toList();
        logger.info("Number of 9-letter words containing 'a' or 'i': " + nineLetterWordsWithAorI.size());

        List<String> result = new ArrayList<>();
        for (String word : nineLetterWordsWithAorI) {
            List<String> containedWords = getContainedWords(word, trie);
            if (!containedWords.isEmpty()) {
                result.add(word);
                logger.info("Found: " + word + " made up of: " + String.join(", ", containedWords));
            }
        }

        logger.info("Time taken: " + start.until(Instant.now(), ChronoUnit.MILLIS) + "ms");
        logger.info("Number of 9-letter words made up of other words: " + result.size());
        logger.info("Words: " + String.join(", ", result));
    }

    public static List<String> getContainedWords(String word, Trie trie) {
        if (word.length() == 1) {
            return trie.isWord(word) ? List.of(word) : List.of();
        }
        if (!trie.isWord(word)) {
            return List.of();
        }
        for (int i = 0; i < word.length(); i++) {
            String wordWithOneLetterRemoved = getWordsAfterRemovingOneLetter(word, i);
            List<String> containedWords = getContainedWords(wordWithOneLetterRemoved, trie);
            if (!containedWords.isEmpty()) {
                return new ArrayList<>(containedWords) {{
                    add(word);
                }};
            }
        }
        return List.of();
    }

    public static boolean isValid(String word) {
        return word.length() == 9 && (word.contains("A") || word.contains("I"));
    }

    public static String getWordsAfterRemovingOneLetter(String word,
                                                    int indexToRemoveAt) {
        String firstPart = word.substring(0, indexToRemoveAt);
        String secondPart = word.substring(indexToRemoveAt + 1);
        return firstPart + secondPart;
    }


}