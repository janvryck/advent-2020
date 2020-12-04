# Advent of Code 2020

![Tests](https://github.com/janvryck/advent-2020/workflows/runTests/badge.svg?branch=main)

## Goals

* Attend in the Advent of Code 2020 challenges  
* Use `Kotlin` to familiarize myself with the language features  
* Try to keep up with the assignments
  * *Stretch goal*: completion would be amazing  

## Lessons Learned
### Day 01
* Setting up a Kotlin project with Gradle
* Kotlin [primary constructors](https://kotlinlang.org/docs/reference/classes.html#constructors)
### Day 02
* Abstract classes
### Day 03
#### Kotlin
* [Data classes](https://kotlinlang.org/docs/reference/data-classes.html)  
* [Operator overloading](https://kotlinlang.org/docs/reference/operator-overloading.html)
* [Sequences](https://kotlinlang.org/docs/reference/sequences.html) *(mobbing)*
#### GitHub
* Set up a GH action for automated testing through Gradle
* Set up PR checks 
#### Mobbing
Additional refactoring after mobbing lunch-session:
* Rename `Trajectory` to `Slope` for more accuracy
* Introduced `Mountain` abstraction
* Build a *trajectory* of `Point`s for a given `Slope`

### Day 04
* [Maps](https://kotlinlang.org/docs/reference/map-operations.html)
* [`Number`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-number/) instead of `Int`/`Long`
* [Constructors](https://kotlinlang.org/docs/reference/classes.html#constructors) and `init` block
