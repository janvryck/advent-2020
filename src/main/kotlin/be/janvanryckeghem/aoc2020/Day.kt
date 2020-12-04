package be.janvanryckeghem.aoc2020

abstract class Day(inputFile: String) {

    abstract val DAY: String

    private val readInputfile = this::class.java.classLoader.getResource(inputFile)!!.readText()
    abstract val parseInput: (String) -> Any

    fun part1(): Number {
        val input: String = readInputfile
        return solvePart1(input)
    }

    fun part2(): Number {
        val input: String = readInputfile
        return solvePart2(input)
    }

    abstract fun solvePart1(input: String): Number
    abstract fun solvePart2(input: String): Number

}