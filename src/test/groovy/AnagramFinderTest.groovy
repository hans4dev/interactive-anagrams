import spock.lang.Specification

class AnagramFinderTest extends Specification {

    def "true for single word anagrams"(String text1, String text2) {
        expect: "true is returned when passed to predicate method"
        AnagramFinder.areAnagrams(text1, text2)

        where: "two texts which are anagrams"
        text1    | text2
        "listen" | "silent"
        "evil"   | "vile"
        "no"     | "on"
    }

    def "false for single word non-anagrams"(String text1, String text2) {
        expect: "false is returned when passed to predicate method"
        !AnagramFinder.areAnagrams(text1, text2)

        where: "two texts which are no anagrams"
        text1    | text2
        "listen" | "list"
        "evil"   | "exil"
    }

    def "fails fast for null, blank or single-letter inputs"(String text1, String text2) {
        when: "minimal requirements not met"
        AnagramFinder.areAnagrams(text1, text2)

        then: "throws an illegal argument exception with corrective help message"
        def exception = thrown(IllegalArgumentException)
        exception.message == 'must be not null and have a minimum length of 2'

        where: "two texts which are no anagrams"
        text1    | text2
        "empty"  | ""
        "blank"  | " "
        "1 char" | "A"
        "null"   | null
        "null"   | null
    }

    def "true for sentences that are anagrams (ignore letter-case, whitespace and punctuation)"(String text1, String text2) {
        expect: "true is returned when passed to predicate method"
        AnagramFinder.areAnagrams(text1, text2)

        where: "two texts which are anagrams"
        text1                 | text2
        "funeral"             | "real fun"
        "New York Times"      | "monkeys write"
        "She Sells Sanctuary" | "Santa; shy, less cruel"
    }

}
