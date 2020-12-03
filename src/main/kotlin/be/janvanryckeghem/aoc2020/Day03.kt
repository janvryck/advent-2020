package be.janvanryckeghem.aoc2020

class Day03(inputFile: String): Day(inputFile) {
    override val DAY: String = "Day 03"

    override val parseInput: (String) -> List<CharArray> = { input ->
        input.lines()
            .filter { s -> s.isNotEmpty() }
            .map { it.toCharArray()}
    }

    override fun solvePart1(input: String): Int {
        val map = parseInput(input)
        val width = map[0].size
        var col = 0
        var trees = 0
        for (row in map.indices) {
            trees = if (map[row][col] == '#') trees.inc() else trees
            col += 3
            col %= width
        }
        return trees
    }

    override fun solvePart2(input: String): Int {
        TODO("Not yet implemented")
    }
}