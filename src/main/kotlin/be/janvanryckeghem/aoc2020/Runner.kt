package be.janvanryckeghem.aoc2020

import be.janvanryckeghem.aoc2020.day01.Day01
import kotlin.system.measureTimeMillis

var solution: String? = null

fun main() {
    timed { Day01("d01").solve() }
}

fun timed(solve: () -> String) {
    println("Solved in ${measureTimeMillis { solution = solve() }}ms")
}