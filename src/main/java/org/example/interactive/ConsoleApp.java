package org.example.interactive;

import de.codeshelf.consoleui.elements.PromptableElementIF;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleApp {
    private static final String MENU = "menu";

    private enum MenuItem {
        TEST("TEST if two texts are anagrams and add the to the input history."), FIND("FIND anagrams in the input history for a text."), EXIT("EXIT and clear the input history.");

        private final String label;

        MenuItem(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public static void loopMenuUntilExit(AnagramFinder anagramFinder) {
        ConsolePrompt prompt = new ConsolePrompt();
        try {
            while (true) {
                System.out.println(ansi().render(String.format("Current history recorded @|yellow %d inputs|@.", anagramFinder.countInputs())).toString());
                MenuItem chosenFeature = chooseFeatureFromMenu(prompt);
                switch (chosenFeature) {
                    case TEST:
                        testAnagrams(prompt, anagramFinder);
                        break;
                    case FIND:
                        findAnagrams(prompt, anagramFinder);
                        break;
                    case EXIT:
                        return;
                    default:
                        System.out.println(String.format("Feature for menu item '%s' is not implemented! Please choose another one.", chosenFeature));
                }
            }
        } catch (IOException e) {
            System.err.println("Terminating because a console-related error occurred: " + e.getMessage());
            System.exit(1);
        } finally {
            try {
                TerminalFactory.get().restore();
            } catch (Exception e) {
                System.err.println("Terminating because a console-related error occurred: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    private static MenuItem chooseFeatureFromMenu(ConsolePrompt prompt) throws IOException {
        List<PromptableElementIF> menu = prompt.getPromptBuilder().createListPrompt().name(MENU).message("Please choose a feature:").newItem(MenuItem.TEST.name()).text(MenuItem.TEST.getLabel()).add().newItem(MenuItem.FIND.name()).text(MenuItem.FIND.getLabel()).add().newItem(MenuItem.EXIT.name()).text(MenuItem.EXIT.getLabel()).add().addPrompt().build();
        return MenuItem.valueOf(((ListResult) prompt.prompt(menu).get(MENU)).getSelectedId());
    }

    private static boolean invalid(InputResult inputResult) {
        try {
            AnagramFinder.validate(inputResult.getInput());
        } catch (IllegalArgumentException e) {
            System.out.println(ansi().bg(RED).fg(BLACK).a("Invalid input: " + e.getMessage()).reset().toString());
            return true;
        }
        return false;
    }

    private static void findAnagrams(ConsolePrompt prompt, AnagramFinder anagramFinder) throws IOException {
        List<PromptableElementIF> searchPhrasePrompt = prompt.getPromptBuilder().createInputPrompt().name("searchPhrase").message("Find anagram for (min. 2 letters):").addPrompt().build();

        System.out.println("Find matching anagrams in the input history recorded so far.");
        InputResult searchPhrase;
        do {
            searchPhrase = (InputResult) prompt.prompt(searchPhrasePrompt).get("searchPhrase");
        } while (invalid(searchPhrase));

        Collection<String> foundAnagrams = anagramFinder.findAnagramsFor(searchPhrase.getInput());
        if (foundAnagrams.isEmpty()) {
            System.out.println(ansi().fg(YELLOW).a("❌ Sorry,no anagrams found!").reset().toString());
        } else {
            System.out.println(ansi().fg(BLUE).a(String.format("✅ Found %d anagrams: %s", foundAnagrams.size(), foundAnagrams)).reset().toString());
        }
    }

    private static void testAnagrams(ConsolePrompt prompt, AnagramFinder anagramFinder) throws IOException {
        List<PromptableElementIF> subjectPrompt = prompt.getPromptBuilder().createInputPrompt().name("subject").message("Text 1 (min. 2 letters):").addPrompt().build();

        System.out.println("Test if two texts are anagrams.");
        InputResult subject;
        do {
            subject = (InputResult) prompt.prompt(subjectPrompt).get("subject");
        } while (invalid(subject));

        List<PromptableElementIF> phrasePrompt = prompt.getPromptBuilder().createInputPrompt().name("phrase").message("Text 2 (min. 2 letters):").addPrompt().build();
        InputResult phrase;
        do {
            phrase = (InputResult) prompt.prompt(phrasePrompt).get("phrase");
        } while (invalid(phrase));


        boolean hasEnteredAnagrams = anagramFinder.areAnagrams(subject.getInput(), phrase.getInput());
        if (hasEnteredAnagrams) {
            System.out.println(ansi().fg(GREEN).a("✅ Great, you just found an anagram!").reset().toString());
        } else {
            System.out.println(ansi().fg(RED).a("❌ Sorry, these are no anagrams.").reset().toString());
        }
    }

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        System.out.println(ansi().bg(BLUE).fg(BLACK).a("============================ Interactive Anagrams ============================").reset().toString());

        loopMenuUntilExit(new AnagramFinder());

        System.out.println("Thanks for your valuable input! Have a good day \uD83D\uDE0E");
        System.exit(0);
    }
}
