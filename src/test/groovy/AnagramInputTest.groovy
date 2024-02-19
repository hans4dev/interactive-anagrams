import spock.lang.Specification

class AnagramInputTest extends Specification {

    def "sorts letters of anagrams to identical canonical form"() {
        given: "3 anagrammatic inputs"
        def input1 = "listen";
        def input2 = "enlist";
        def input3 = "silent";

        when: "sorting each"
        def sortedLetters1 = AnagramInput.sortedLetters(input1);
        def sortedLetters2 = AnagramInput.sortedLetters(input2);
        def sortedLetters3 = AnagramInput.sortedLetters(input3);

        then: "sorted letters of all are equal"
        sortedLetters1 == sortedLetters2
        sortedLetters2 == sortedLetters3
    }
}
