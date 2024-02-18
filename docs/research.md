# Research

The test and verification of anagrams is a common programming puzzle and algorithmic challenge to solve. Thus a
web-search results in many hits.

These 4 hits represent authoritative sources, each a different category (encyclopedia/reference, tutorial/article, community/forum, blog post):

* Wikipedia (English): [Anagram](https://en.wikipedia.org/wiki/Anagram)
* Baeldung: [Check if Two Strings Are Anagrams in Java](https://www.baeldung.com/java-strings-anagrams)
* Stackoverflow: [Highest scored 'java+anagram' questions](https://stackoverflow.com/questions/tagged/java+anagram)
* Personal Blog _Global Nerdy_ (2020): [Programmer interview challenge 1: Anagram](https://www.globalnerdy.com/2020/06/10/programmer-interview-challenge-1-anagram/)

Wikipedia is useful for the definition, terminology and to give context, or find references.

The next sections will summarize the implementation approaches that are explained in peer-edited articles, developer communities, or well-curated blogs.

## Baeldung

Baeldung is credible as a known and editor-reviewed tutorial site in the Java world.

The article is based on the Wikipedia definition for anagrams, which it cites:
> [..] an anagram is a word or phrase formed by rearranging the letters of a different word or phrase.

And explains:
> [..] an anagram of a string is another string with exactly the same quantity of each character in it, in any order.

It proposes three ways of solving:

1. Check by Sorting: Convert strings to char-arrays then sort and compare.
   Their normalized/canonical forms should be the same.
   Advantages:
    * easy to understand and implement
    * normalized form can be used as key for indexed lookup
      Disadvantages:
    * O(n log n) because copying and sorting an array of n characters
2. Check by Counting: Count the number of occurrences of each character and compare.
   Their histograms should be the same.
   Advantages:
    * Fast: O(n). Optimize: use single histogram, increment for one, decrement for the other input, so that all values
      zero proofs anagrams.
    * Portability (since low-level functions are used: for-loop, char-array, basic arithmetic)
      Disadvantages:
    * Size: Histogram as array with size, either 26 for alphabet, 256 for ASCII character range, or more for
      multiple-byte character sets such as UTF-8.
    * More and complex code to maintain, less abstract and less use of Java library-functions.
3. Check with MultiSet: Count as in (2.) supported by MultiSet datastructure, to easily compare.
   Advantages:
    * O(n) time without having to declare a big fixed-size counting array.
      Disadvantages:
    * Adds a 3rd-party Guava dependency from Google (to understand, update).

Last the improvement of letter-based anagrams is shown, that preprocesses the input strings. This will ignore
punctuation and whitespace.

```java
String preprocess(String source) {
    return source.replaceAll("[^a-zA-Z]", "").toLowerCase();
}
```

## Stackoverflow
Stackoverflow discusses specific issues, where the quality of questions and answers can vary and should be received with care and in context.

The 10-year old question [How to check if two words are anagrams?](https://stackoverflow.com/questions/15045640/how-to-check-if-two-words-are-anagrams)
has achieved 51 scores and collected 37 answers, top 3 in a score-range of 50 to 100.

Famous Java gold-badge winner [Stephen C's answer](https://stackoverflow.com/a/26070946/5730279), seems
well-maintained (last edit 2022) and summarizes the algorithmic complexity for most of the given answers.
In addition, he emphasizes the rare and mathematical approach of prime-factorization, and he explains how and why to
improve the char sorting approach to deal with input string containing emojis and other Unicode code-points outside of
the BMP (plane 0).

## Personal Blog _Global Nerdy_ (2020)

Shows a solution in context:
> Anagram” is a common programming challenge that I’ve seen issued to prospective developers in technical interviews:
> Write a program or function that can tell if two given words are anagrams of each other.

Uses Python to show simple code. Makes use of sorting and sting manipulation like lower-case letter conversion and
striping.

This post is revisited later in [Programmer interview challenge 1, revisited: Revising “Anagram” in Python and implementing it in JavaScript : Global Nerdy](https://www.globalnerdy.com/2020/06/11/programmer-interview-challenge-1-revisited-revising-anagram-in-python-and-implementing-it-in-javascript/).
There the `sortLetters()` function as refactoring for DRY (Don’t Repeat Yourself) instead of WET (Write Everything
Twice).

## Conclusion

The simple sorting approach is widely recommended for many benefits:

1. High readability, easy to understand and explain. Still it has quite acceptable performance: O(N log N).
2. Even modern input like special Unicode characters (e.g. emojis) can be supported with some adjustments.
3. Furthermore the resulting normalized string (canonical form) can be reused as lookup key.