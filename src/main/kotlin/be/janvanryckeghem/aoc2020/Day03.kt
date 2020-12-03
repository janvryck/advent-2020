package be.janvanryckeghem.aoc2020

class Day03(inputFile: String): Day(inputFile) {
    override val DAY: String = "Day 03"

    override val parseInput: (String) -> List<CharArray> = { input ->
        input.lines()
            .filter { s -> s.isNotEmpty() }
            .map { it.toCharArray()}
    }

    override fun solvePart1(input: String): Long {
        val map = parseInput(input)
        return treesOnTrajectory(map)
    }

    override fun solvePart2(input: String): Long {
        val map = parseInput(input)
        return treesOnTrajectory(map, 1) * treesOnTrajectory(map, 3) * treesOnTrajectory(map, 5) * treesOnTrajectory(map, 7) * treesOnTrajectory(map, 1, 2)
    }

    private fun treesOnTrajectory(map: List<CharArray>, right: Int = 3, down: Int = 1): Long {
        val width = map[0].size
        var col = 0
        var trees: Long = 0
        for (row in map.indices step down) {
            trees = if (map[row][col] == '#') trees.inc() else trees
            col += right
            col %= width
        }
        return trees
    }
}