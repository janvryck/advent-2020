package be.janvanryckeghem.aoc2020

import kotlin.system.measureTimeMillis

var solution: Any? = null

fun main() {
    timed { Day01("d01").part1() }
    println("\tD1.1: $solution")
    timed { Day01("d01").part2() }
    println("\tD1.2: $solution")
    timed { Day02("d02").part1() }
    println("\tD2.1: $solution")
    timed { Day02("d02").part2() }
    println("\tD2.2: $solution")
}

fun timed(solve: () -> Any) {
    println("Solved in ${measureTimeMillis { solution = solve() }}ms:")
}