package be.janvanryckeghem.aoc2020

import kotlin.system.measureTimeMillis

val puzzles: List<Day> = listOf(
    Day01(),
    Day02(),
    Day03(),
    Day04(),
    Day05(),
    Day06(),
    Day07(),
    Day08(),
    Day09(),
    Day10(),
    Day11(),
    Day12(),
    Day13(),
    Day14(),
    Day15(),
    Day16(),
    Day17(),
    Day18()
)

fun main() {
    val totalRuntime = measureTimeMillis {
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
    println("\nRan all puzzles in ${totalRuntime}ms")
}