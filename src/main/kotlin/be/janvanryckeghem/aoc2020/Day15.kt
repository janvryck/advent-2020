package be.janvanryckeghem.aoc2020

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
        val memory = input.withIndex().associateBy({ it.value }, { it.index + 1 }).toMutableMap()
        var last = input.last()
        for (turn in memory.size until turns) {
            val current = last
            last = if (current in memory) turn - memory[current]!! else 0
            memory[current] = turn
        }
        return last
    }
}