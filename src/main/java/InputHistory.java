import java.util.*;
import java.util.stream.IntStream;

public class InputHistory {

    private final Map<String, Set<String>> canonicalFormToInputs = new HashMap<>();

    public Collection<String> findAnagramsFor(String searchPhrase) {
        return canonicalFormToInputs.get(AnagramInput.sortedLetters(searchPhrase));
    }

    public int size() {
        return canonicalFormToInputs.values().stream().flatMapToInt(set -> IntStream.of(set.size())).sum();
    }

    /**
     * Adds the given text to the input history and returns its canonical form (sorted letters).
     *
     * <p>
     * Uses the canonical form (sorted-letters) as key. Thus the input must have at least 1 letter.
     * The input is trimmed and stored as value.
     * </p>
     *
     * @param text a text which must contain at least 2 letters
     * @return the canonical form (sorted-letters) that was used as key
     * @throws IllegalArgumentException if given text is null, blank or is less than 1 letter after cleaning
     */
    public String add(String text) throws IllegalArgumentException {
        AnagramInput.requireLetters(text, 1);

        String key = AnagramInput.sortedLetters(text);
        String value = text.trim();
        canonicalFormToInputs.computeIfAbsent(key, k -> new HashSet<>()).add(value);

        return key;
    }
}
