import java.util.Arrays;

import static java.util.Arrays.*;

/**
 * Uses the approach "sort character arrays" to verify anagrams.
 * Considers letters (a-z) only while letter-case, whitespace and punctuation or other characters are ignored.
 * Uses {@link AnagramInput} for validation and cleaning.
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
     * @throws IllegalArgumentException if any argument is null, blank or cleaned less than 2 letters
     */
    public static boolean areAnagrams(String subject, String phrase) throws IllegalArgumentException {

        char[] subjectLetters = AnagramInput.requireLetters(subject, 2).toCharArray();
        char[] phraseLetters = AnagramInput.requireLetters(phrase, 2).toCharArray();

        if (subjectLetters.length != phraseLetters.length) {
            return false;
        }

        sort(subjectLetters);
        sort(phraseLetters);

        return Arrays.equals(subjectLetters, phraseLetters);
    }

}
