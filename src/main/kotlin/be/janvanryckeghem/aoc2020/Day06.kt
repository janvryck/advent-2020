package be.janvanryckeghem.aoc2020

class Day06(inputFile: String = "d06"): Day(inputFile) {
    override val DAY: String = "DAY 06"
    override val parseInput: (String) -> List<String> = { input ->
        input.split("\n\n")
    }
    private val alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray().toSet()

    override fun solvePart1(input: String): Number {
        return parseInput(input)
            .map { s -> s.lines() }
            .map { l -> l.joinToString().replace(",", "")}
            .map { s -> s.toCharArray().filter { c -> c.isLetter() }.distinct().count() }
            .sum()
    }

    override fun solvePart2(input: String): Number {
        return parseInput(input)
            .map { s -> s.lines() }
            .map { s -> s.fold(alphabet){
                    common, string -> common.intersect(string.toCharArray().toSet())
            } }
            .map { s -> s.size }
            .sum()
    }
}