package be.janvanryckeghem.aoc2020

import be.janvanryckeghem.aoc2020.day01.Day01
import kotlin.system.measureTimeMillis

var solution: String? = null

fun main() {
    timed { Day01("d01").part1() }
    println("D1.1: $solution")
    timed { Day01("d01").part2() }
    println("D1.2: $solution")
}

fun timed(solve: () -> String) {
    println("Solved in ${measureTimeMillis { solution = solve() }}ms")
}