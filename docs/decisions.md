# Decisions taken

Instead of long ADRs, the decisions are formulated as lightweight [Y-statements](https://adr.github.io/#sustainable-architectural-decisions) ("why" statements):

> In the context of <use case/user story u>, 
> facing <concern c> 
> we decided for <option o> 
> and neglected <other options>, 
> to achieve <system qualities/desired consequences>, 
> accepting <downside d/undesired consequences>, 
> because <additional rationale>.

In the context of .. 

1. **DOCS**: documenting the solution,
   facing unknown style and zero-setup

   I decided for a traditional **README, 3 technical docs, plus JavaDocs**

   to express my thoughts and communicate the "why" of this solution
   because I know from experience that this is important and helps to understand the code in the future.
2. **TEST**: having sufficient tests usage-example,
   facing the learn-and-share aspect
   
   I decided to introduce **Spock for unit-tests**
   
   to document my specifications
   accepting that the language is Groovy instead of Java or Kotlin, because it is comprehensible for Java-devs.
3. **GIT**: showing my workflow transparently
   when publishing via a Git-repo,
   
   I decided to use **small semantic commits following on TDD**
   
   to iterate from inside to the outside, feature after feature.
4. **IMPLEMENT**: feature #1 (test 2 texts for anagrams), 
   facing an uncertain review 
   
   I decided for the **simple sorting-implementation** 
   
   to achieve readability.
5. **IMPLEMENT**: feature #2 (find anagrams in input-history), 
   facing a responsive console-application 
   
   I decided for an **indexed hash-map using the sorted-letters as key**
   
   to quickly lookup anagrams.
6. **DEPENDENCY**: prototyping a simple demo-app,
   facing time-scarcity while allowing acceptance-tests
   
   I decided to add a **3rd-party dependency for the UI** part.
   
   to achieve a robust user experience, because the freedom was explicitly given
7. **ARCHITECTURE**: a simple but reasonable architecture,
   facing the tiny scope and boundary of this puzzle,
   
   I decided to **separate UI/presentation and domain/logic** into packages (interactive and anagram)
   
   to allow for reuse and exchange where a facade serves as gateway.