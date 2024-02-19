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
     * Verifies if two texts are anagrams of each other.
     *
     * @param subject a word or phrase
     * @param phrase  another word or phrase
     * @return true if phrase is an anagram of subject, false otherwise
     * @throws IllegalArgumentException if any argument is null, blank or trimmed less than 2 chars
     */
    public static boolean areAnagrams(String subject, String phrase) throws IllegalArgumentException {

        requireMinLength(subject, 2);
        requireMinLength(phrase, 2);

        char[] subjectChars = subject.toCharArray();
        sort(subjectChars);
        char[] phraseChars = phrase.toCharArray();
        sort(phraseChars);

        return Arrays.equals(subjectChars, phraseChars);
    }

    private static void requireMinLength(String text, int minimumLength) {
        if (text == null || text.isBlank() || text.trim().length() < minimumLength) {
            throw new IllegalArgumentException(String.format("must be not null and have a minimum length of %d", minimumLength));
        }
    }

}
