import spock.lang.Specification

class AnagramFinderTest extends Specification {

    def "true for single word anagrams"(String text1, String text2) {
        expect: "true is returned when passed to predicate method"
        AnagramFinder.areAnagrams(text1, text2)

        where: "two texts which are anagrams"
        text1    | text2
        "listen" | "silent"
        "evil"   | "vile"
    }
}
