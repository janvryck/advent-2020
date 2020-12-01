package be.janvanryckeghem.aoc2020.day01

class Day01(inputFile: String) {

    private val readInputfile = this::class.java.classLoader.getResource(inputFile)?.readText()

    fun solve(): String {
        val input: String? = readInputfile
        return if (input === null) "Could not read input." else findSolution(input)
    }

    fun findSolution(input: String): String {
        val numbers = input.lines().filter { s -> s.isNotEmpty() }.map(String::toInt)
        for (number in numbers) {
            if (numbers.contains(2020 - number)) {
                return (number * (2020 - number)).toString()
            }
        }

        return "No solution found"
    }

}