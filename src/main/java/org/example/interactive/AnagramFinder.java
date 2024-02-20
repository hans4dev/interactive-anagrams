package org.example.interactive;

import org.example.interactive.anagram.Anagrams;
import org.example.interactive.anagram.InputHistory;

import java.util.Set;

/**
 * Acts as a facade to simplify access to features:
 * <ol>
 *     <li>Test for anagrams: implemented by {@link Anagrams}</li>
 *     <li>Record input and find anagrams in history: implemented by {@link InputHistory}</li>
 * </ol>
 */
public class AnagramFinder {

    private final InputHistory inputHistory = new InputHistory();

    public boolean areAnagrams(String subject, String phrase) {
        inputHistory.add(subject);
        inputHistory.add(phrase);

        return Anagrams.areAnagrams(subject, phrase);
    }

    public Set<String> findAnagramsFor(String input) {
        return inputHistory.findAnagramsFor(input);
    }

    public int countInputs() {
        return inputHistory.size();
    }
}
