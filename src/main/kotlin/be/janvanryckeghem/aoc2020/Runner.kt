package be.janvanryckeghem.aoc2020

import kotlin.system.measureTimeMillis

val puzzles: List<Day> = listOf(
    Day01(),
    Day02(),
    Day03()
)

fun main() {
    var solution: Any? = null
    var runTime: Long
    puzzles.forEach { puzzle ->
        println("--- ${puzzle.DAY} ---")
        runTime = measureTimeMillis { solution = puzzle.part1() }
        println("> Part 1:\t$solution")
        println("  > in ${runTime}ms")
        runTime = measureTimeMillis { solution = puzzle.part2() }
        println("> Part 2:\t$solution")
        println("  > in ${runTime}ms")
    }
}