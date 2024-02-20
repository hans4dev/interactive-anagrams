package anagram;

import java.util.*;

public class AnagramInput {

    static String sortedLetters(String text) {
        char[] chars = letters(text).toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    static String letters(String text) {
        return text.trim().toLowerCase().replaceAll("[^a-z]+", "");
    }

    static String requireLetters(String text, int minimumLength) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("must be not null, blank or empty");
        }

        String letters = letters(text);
        if (letters.length() < minimumLength) {
            throw new IllegalArgumentException(String.format("must contain at least %d letters", minimumLength));
        }

        return letters;
    }
}
