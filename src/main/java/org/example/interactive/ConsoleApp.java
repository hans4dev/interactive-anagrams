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

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleApp {
    private static final String MENU = "menu";

    private enum MenuItem {
        TEST("TEST if two texts are anagrams and add the to the input history."),
        FIND("FIND anagrams in the input history for a text."),
        EXIT("EXIT and clear the input history.");

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
                MenuItem chosenFeature = chooseFeatureFromMenu(anagramFinder, prompt);
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
            System.out.println("Terminating because a console-related error occurred: " + e.getMessage());
            System.exit(1);
        } finally {
            try {
                TerminalFactory.get().restore();
            } catch (Exception e) {
                System.out.println("Terminating because a console-related error occurred: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    private static MenuItem chooseFeatureFromMenu(AnagramFinder anagramFinder, ConsolePrompt prompt) throws IOException {
        String dynamicText = String.format("Current input history has %d entries. Please choose a feature:", anagramFinder.countInputs());
        List<PromptableElementIF> menu = prompt.getPromptBuilder().createListPrompt()
                .name(MENU).message(dynamicText).newItem(MenuItem.TEST.name()).text(MenuItem.TEST.getLabel()).add()
                .newItem(MenuItem.FIND.name()).text(MenuItem.FIND.getLabel()).add()
                .newItem(MenuItem.EXIT.name()).text(MenuItem.EXIT.getLabel()).add()
                .addPrompt()
                .build();
        return MenuItem.valueOf(((ListResult) prompt.prompt(menu).get(MENU)).getSelectedId());
    }

    private static void findAnagrams(ConsolePrompt prompt, AnagramFinder anagramFinder) throws IOException {
        System.out.println("Please enter a text to find matching anagrams in the input history recorded so far.");
        List<PromptableElementIF> searchPhrasePrompt = prompt.getPromptBuilder().createInputPrompt().name("searchPhrase").message("Find anagram for:").addPrompt().build();
        InputResult searchPhrase = (InputResult) prompt.prompt(searchPhrasePrompt).get("searchPhrase");
        Collection<String> foundAnagrams = anagramFinder.findAnagramsFor(searchPhrase.getInput());
        System.out.println("Found anagrams: " + foundAnagrams);
    }

    private static void testAnagrams(ConsolePrompt prompt, AnagramFinder anagramFinder) throws IOException {
        System.out.println("Please enter two texts to test if they are anagrams.");
        List<PromptableElementIF> subjectPrompt = prompt.getPromptBuilder().createInputPrompt().name("subject").message("Text 1:").addPrompt().build();
        InputResult subject = (InputResult) prompt.prompt(subjectPrompt).get("subject");

        List<PromptableElementIF> phrasePrompt = prompt.getPromptBuilder().createInputPrompt().name("phrase").message("Text 2:").addPrompt().build();
        InputResult phrase = (InputResult) prompt.prompt(phrasePrompt).get("phrase");

        boolean hasEnteredAnagrams = anagramFinder.areAnagrams(subject.getInput(), phrase.getInput());
        if (hasEnteredAnagrams) {
            System.out.println("Great, you just found an anagram!");
        } else {
            System.out.println("Sorry, these are no anagrams.");
        }
    }

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        System.out.println(ansi().render("@|blue Interactive|@ Anagram @|green Tester|@"));

        loopMenuUntilExit(new AnagramFinder());

        System.out.println("Thanks for your valuable input! Have a good day \uD83D\uDE0E");
        System.exit(0);
    }
}
