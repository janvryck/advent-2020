package be.janvanryckeghem.aoc2020

class Day03(inputFile: String) : Day(inputFile) {
    override val DAY: String = "Day 03"

    override val parseInput: (String) -> List<CharArray> = { input ->
        input.lines()
            .filter { s -> s.isNotEmpty() }
            .map { it.toCharArray() }
    }

    override fun solvePart1(input: String): Long {
        val map = parseInput(input)
        val trajectory = Trajectory(3, 1)
        return treesOnTrajectory(map, trajectory)
    }

    override fun solvePart2(input: String): Long {
        val map = parseInput(input)
        val trajectories =
            listOf(Trajectory(1, 1), Trajectory(3, 1), Trajectory(5, 1), Trajectory(7, 1), Trajectory(1, 2))
        return trajectories
            .map { trajectory -> treesOnTrajectory(map, trajectory) }
            .reduce(Long::times)
    }

    private fun treesOnTrajectory(map: List<CharArray>, trajectory: Trajectory): Long {
        val upperBound = Point(map[0].size, map.size)
        var position = Point()
        var trees: Long = 0

        while (position.y < upperBound.y) {
            if (map[position.y][position.x % upperBound.x] == '#') trees++
            position = position.plus(trajectory)
        }

        return trees
    }

    data class Point(val x: Int = 0, val y: Int = 0) {
        operator fun plus(increment: Trajectory): Point {
            return Point(x + increment.dX, y + increment.dY)
        }
    }
    data class Trajectory(val dX: Int, val dY: Int)
}