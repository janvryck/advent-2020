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
        var lastNumber = input.last()
        for (currentTurn in memory.size until turns) {
            memory.compute(lastNumber) { _, lastTurn ->
                lastNumber = currentTurn.minus(lastTurn ?: currentTurn)
                currentTurn
            }
        }
        return lastNumber
    }
}