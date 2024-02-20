# Design

Two packages separate UI/presentation and domain/logic (separation of concerns).

The design is very simple and broken up in [**CRC cards**](https://en.wikipedia.org/wiki/Class-responsibility-collaboration_card), represented for each of the classes in a table-row below.

## Package `interactive`

The console application uses following classes:

| Class         | Responsibility                                                                                        | Collaboration                        |
|---------------|-------------------------------------------------------------------------------------------------------|--------------------------------------|
| ConsoleApp    | Main-method (entry-point), controls text-based UI (menu entries, prompts), event-loop, error-handling | AnagramFinder                        |
| AnagramFinder | Facade to simplify validation and execute the 2 feature commands (test, find)                         | AnagramInput, Anagrams, InputHistory |


## Package `interactive.anagram`

The anagram package contains domain-related classes with responsibilities:

| Class        | Responsibility                                                                                     | Collaboration          |
|--------------|----------------------------------------------------------------------------------------------------|------------------------|
| AnagramInput | Validation, cleaning and key-generation for anagram-related text input                             |                        |
| Anagrams     | Static utility-class tests if two texts are anagrams (predicate)                                   | AnagramInput           |
| InputHistory | Map wrapper that allows to add valid anagram inputs, find anagrams for search-phrase, count inputs | AnagramInput, Anagrams |
