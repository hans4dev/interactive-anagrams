package org.example.interactive.anagram;

import java.util.*;

/**
 * Text-processing for anagram-related input.
 *
 * <ol>
 *     <li>Validation: {@link #requireLetters(String, int)}</li>
 *     <li>Cleaning and conversion: {@link #letters(String)}</li>
 *     <li>Key/index creation (Canonical form): {@link #sortedLetters(String)}</li>
 * </ol>
 */
public class AnagramInput {

    public static String requireLetters(String text, int minimumLength) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("must be not null, blank or empty");
        }

        String letters = letters(text);
        if (letters.length() < minimumLength) {
            throw new IllegalArgumentException(String.format("must contain at least %d letters", minimumLength));
        }

        return letters;
    }

    static String sortedLetters(String text) {
        char[] chars = letters(text).toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private static String letters(String text) {
        return text.trim().toLowerCase().replaceAll("[^a-z]+", "");
    }
}
