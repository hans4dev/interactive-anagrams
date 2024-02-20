import java.util.*;
import java.util.stream.IntStream;

public class InputHistory {

    private final Map<String, Set<String>> canonicalFormToInputs = new HashMap<>();

    /**
     * Search for matching anagrams in recorded input history.
     *
     * @param searchPhrase a phrase to search for anagrams, must be at least 2 letters
     * @return a set of trimmed inputs that are found as anagrams to the given search phrase (de-duplicated)
     */
    public Set<String> findAnagramsFor(String searchPhrase) {
        AnagramInput.requireLetters(searchPhrase, Anagrams.MINIMUM_2_LETTERS);

        String searchKey = AnagramInput.sortedLetters(searchPhrase);

        return canonicalFormToInputs.get(searchKey);
    }

    /**
     * Counts all inputs that are recorded in history (added as valid).
     *
     * @return the count of input values (de-duplicated)
     */
    public int size() {
        return canonicalFormToInputs.values().stream().flatMapToInt(set -> IntStream.of(set.size())).sum();
    }

    /**
     * Adds the given text to the input history and returns its canonical form (sorted letters).
     *
     * <p>
     * Uses the canonical form (sorted-letters) as key. Thus the input must have at least 2 letters.
     * The input is trimmed and stored as value.
     * </p>
     *
     * @param text a text which must contain at least 2 letters
     * @return the canonical form (sorted-letters) that was used as key
     * @throws IllegalArgumentException if given text is null, blank or is less than 2 letters after cleaning
     */
    public String add(String text) throws IllegalArgumentException {
        AnagramInput.requireLetters(text, Anagrams.MINIMUM_2_LETTERS);

        String key = AnagramInput.sortedLetters(text);
        String value = text.trim();
        canonicalFormToInputs.computeIfAbsent(key, k -> new HashSet<>()).add(value);

        return key;
    }
}
