package anagram;

import java.util.Arrays;

import static java.util.Arrays.*;

/**
 * Implementation of the simple approach "sort character arrays" to verify anagrams.
 *
 * <h3>Constraints and Design</h3>
 * Follows the definition of anagram.Anagrams from the English Wikipedia.
 * Considers letters (a-z) only while letter-case, whitespace and punctuation or other characters are ignored.
 * Uses {@link AnagramInput} for validation and cleaning.
 *
 * <h3>Usage</h3>
 * A predicate method {@link #areAnagrams(String, String)} can be used to verify anagrams.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Anagrams">Wikipedia (en, 2024-01-09): anagram.Anagrams</a>
 */
public class Anagrams {

    public static final int MINIMUM_2_LETTERS = 2;

    /**
     * Verifies if two texts are anagrams of each other.
     *
     * @param subject a word or phrase
     * @param phrase  another word or phrase
     * @return true if phrase is an anagram of subject, false otherwise
     * @throws IllegalArgumentException if any argument is null, blank or cleaned less than 2 letters
     */
    public static boolean areAnagrams(String subject, String phrase) throws IllegalArgumentException {

        char[] subjectLetters = AnagramInput.requireLetters(subject, MINIMUM_2_LETTERS).toCharArray();
        char[] phraseLetters = AnagramInput.requireLetters(phrase, MINIMUM_2_LETTERS).toCharArray();

        if (subjectLetters.length != phraseLetters.length) {
            return false;
        }

        sort(subjectLetters);
        sort(phraseLetters);

        return Arrays.equals(subjectLetters, phraseLetters);
    }

}
