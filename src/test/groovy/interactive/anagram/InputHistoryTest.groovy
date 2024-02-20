package interactive.anagram

import org.example.interactive.anagram.InputHistory
import spock.lang.Specification

class InputHistoryTest extends Specification {

    def "fresh history is empty"() {
        when:
        def history = new InputHistory()
        then:
        history.size() == 0
    }

    def "inputs are added to history"() {
        given: "input history with 2 anagrams"
        def history = new InputHistory();

        when: "adding 3 texts"
        for (i in 1..3) {
            history.add("text $i")
        }

        then: "history has size 3"
        history.size() == 3
    }

    def "find anagrams for matching phrase"() {
        given: "input history with 2 anagrams"
        def history = new InputHistory();
        history.add("listen")
        history.add("enlist")

        when: "searching for a 3rd anagram"
        def found = history.findAnagramsFor("silent")

        then: "the previous 2 inputs are found"
        found  == ["listen", "enlist"] as Set
    }
}
