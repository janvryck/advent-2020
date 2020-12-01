package be.janvanryckeghem.aoc2020.day01

class Day01(inputFile: String) {

    private val readInputfile = this::class.java.classLoader.getResource(inputFile)?.readText()
    private val parseInput: (String) -> List<Int> = { input -> input.lines()
        .filter { s -> s.isNotEmpty() }
        .map(String::toInt)
        .distinct()
        .sorted()
    }

    fun part1(): String {
        val input: String? = readInputfile
        return if (input === null) "Could not read input." else solvePart1(input)
    }

    fun part2(): String {
        val input: String? = readInputfile
        return if (input === null) "Could not read input." else solvePart2(input)
    }

    private fun solvePart1(input: String): String {
        val numbers = parseInput(input)
        for (number in numbers) {
            if (numbers.contains(2020 - number)) {
                return (number * (2020 - number)).toString()
            }
        }
        return "No solution found"
    }

    private fun solvePart2(input: String): String {
        val numbers = parseInput(input)
        for (i in 0..(numbers.size-2)) {
            for (j in (i+1) until (numbers.size-1))  {
                for (k in (j+1) until numbers.size) {
                    if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                        return (numbers[i] * numbers[j] * numbers[k]).toString()
                    }
                }
            }
        }
        return "No solution found"
    }
}