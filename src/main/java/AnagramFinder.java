import java.util.Arrays;

import static java.util.Arrays.*;

/**
 * Uses the approach "sort character arrays" to verify anagrams.
 * Considers letters (a-z) only, letter-case, whitespace and punctuation or other characters are ignored.
 * <p>
 * A predicate method {{@link #areAnagrams(String, String)} can be used to verify anagrams.
 * </p>
 */
public class AnagramFinder {
    /**
     * Verifies if two texts are anagrams of each other.
     *
     * @param subject a word or phrase
     * @param phrase  another word or phrase
     * @return true if phrase is an anagram of subject, false otherwise
     * @throws IllegalArgumentException if any argument is null, blank or trimmed less than 2 letters
     */
    public static boolean areAnagrams(String subject, String phrase) throws IllegalArgumentException {

        requireMinLength(subject, 2);
        requireMinLength(phrase, 2);

        char[] subjectLetters = letters(subject);
        sort(subjectLetters);
        char[] phraseLetters = letters(phrase);
        sort(phraseLetters);

        return Arrays.equals(subjectLetters, phraseLetters);
    }

    private static char[] letters(String text) {
        return text.trim().toLowerCase().replaceAll("[^a-z]+", "").toCharArray();
    }

    private static void requireMinLength(String text, int minimumLength) {
        if (text == null || text.isBlank() || text.trim().length() < minimumLength) {
            throw new IllegalArgumentException(String.format("must be not null and have a minimum length of %d", minimumLength));
        }
    }

}
