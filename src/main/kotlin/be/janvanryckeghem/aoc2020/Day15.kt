package be.janvanryckeghem.aoc2020

import java.lang.IllegalStateException

class Day15(inputFile: String = "d15") : Day(inputFile) {
    override val DAY: String = "DAY 15"
    override val parseInput: (String) -> List<Int> = { input -> input.split(",").map { it.toInt() } }

    override fun solvePart1(input: String): Number {
        return memoryGame(parseInput(input))
    }

    override fun solvePart2(input: String): Number {
        return memoryGame(parseInput(input), 30_000_000)
    }

    fun memoryGame(input: List<Int>, turns: Int = 2020): Int {
        val memory = mutableMapOf<Int, Int>()
        var last: Int? = null
        repeat(turns - 1) { turn ->
            val current = input.elementAtOrElse(turn) { last!! }

            last = if (current in memory) turn - memory[current]!! else 0
            memory[current] = turn
        }
        return last!!
    }
}