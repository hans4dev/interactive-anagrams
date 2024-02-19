import java.util.*;

public class AnagramInput {

    public static String sortedLetters(String text) {
        char[] chars = letters(text).toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    static String letters(String text) {
        return text.trim().toLowerCase().replaceAll("[^a-z]+", "");
    }

    static String requireLetters(String text, int minimumLength) {
        if (text == null || text.isBlank() || text.trim().length() < minimumLength) {
            throw new IllegalArgumentException(String.format("must be not null and have a minimum length of %d", minimumLength));
        }

        String letters = letters(text);
        if (letters.length() < minimumLength) {
            throw new IllegalArgumentException(String.format("must contain at least %d letters", minimumLength));
        }

        return letters;
    }
}
