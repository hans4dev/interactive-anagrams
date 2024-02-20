package org.example.interactive.anagram


import spock.lang.Specification

class AnagramsTest extends Specification {

    def "true for single word anagrams"(String text1, String text2) {
        expect: "true is returned when passed to predicate method"
        Anagrams.areAnagrams(text1, text2)

        where: "two texts which are anagrams"
        text1    | text2
        "listen" | "silent"
        "evil"   | "vile"
        "no"     | "on"
    }

    def "false for single word non-anagrams"(String text1, String text2) {
        expect: "false is returned when passed to predicate method"
        !Anagrams.areAnagrams(text1, text2)

        where: "two texts which are no anagrams"
        text1    | text2
        "listen" | "list"
        "evil"   | "exil"
    }

    def "fails fast for null, blank or empty"(String text1, String text2) {
        when: "minimal requirements not met"
        Anagrams.areAnagrams(text1, text2)

        then: "throws an illegal argument exception with corrective help message"
        def exception = thrown(IllegalArgumentException)
        exception.message == 'must be not null, blank or empty'

        where: "two texts which are no anagrams"
        text1   | text2
        "empty" | ""
        "blank" | " "
        "null"  | null
    }

    def "requires at least 2 letters (after cleaning)"(String text1, String text2) {
        when: "minimal requirements not met"
        Anagrams.areAnagrams(text1, text2)

        then: "throws an illegal argument exception with corrective help message"
        def exception = thrown(IllegalArgumentException)
        exception.message == 'must contain at least 2 letters'

        where: "two texts where one has less than 2 letters"
        text1    | text2
        "1 char" | "A"
        "She"    | "S__"
        "She"    | "S__ ?!"
    }

    def "true for sentences that are anagrams (ignore letter-case, whitespace and punctuation)"(String text1, String text2) {
        expect: "true is returned when passed to predicate method"
        Anagrams.areAnagrams(text1, text2)

        where: "two texts which are anagrams"
        text1                 | text2
        "funeral"             | "real fun"
        "New York Times"      | "monkeys write"
        "She Sells Sanctuary" | "Santa; shy, less cruel"
    }

    def "false for sentences that are no anagrams (letter length varies)"(String text1, String text2) {
        expect: "false is returned when passed to predicate method"
        !Anagrams.areAnagrams(text1, text2)

        where: "two texts which are anagrams"
        text1                      | text2
        "funeral"                  | "fun"
        "New York Times is longer" | "monkeys write"
        "She Sells Sanctuary"      | "The Cult"
    }

}
