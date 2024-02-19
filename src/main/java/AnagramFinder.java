import java.util.Arrays;

import static java.util.Arrays.*;

/**
 * Uses the approach "sort char-arrays" to verify anagrams.
 * <p>
 * A predicate method {{@link #areAnagrams(String, String)} can be used to verify anagrams.
 * </p>
 */
public class AnagramFinder {
    /**
     * Verifies if two given texts are anagrams of each other.
     *
     * @param subject a word or phrase
     * @param phrase another word or phrase
     * @return true if phrase is an anagram of phrase
     */
    public static boolean areAnagrams(String subject, String phrase) {

        char[] subjectChars = subject.toCharArray();
        sort(subjectChars);
        char[] phraseChars = phrase.toCharArray();
        sort(phraseChars);

        return Arrays.equals(subjectChars, phraseChars);
    }
}
