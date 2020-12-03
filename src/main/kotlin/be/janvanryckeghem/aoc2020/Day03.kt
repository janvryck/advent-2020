package be.janvanryckeghem.aoc2020

class Day03(inputFile: String = "d03") : Day(inputFile) {
    override val DAY: String = "Day 03"

    override val parseInput: (String) -> List<CharArray> = { input ->
        input.lines()
            .filter { s -> s.isNotEmpty() }
            .map { it.toCharArray() }
    }

    override fun solvePart1(input: String): Long {
        val map = parseInput(input)
        val slope = Slope(3, 1)
        return treesOnTrajectory(map, slope)
    }

    override fun solvePart2(input: String): Long {
        val map = parseInput(input)
        val slopes = listOf(Slope(1, 1), Slope(3, 1), Slope(5, 1), Slope(7, 1), Slope(1, 2))
        return slopes
            .map { slope -> treesOnTrajectory(map, slope) }
            .reduce(Long::times)
    }

    private fun treesOnTrajectory(map: List<CharArray>, slope: Slope): Long {
        val mountain = Mountain(map)
        return mountain
            .trajectory(Point(), slope)
            .filter { mountain.hasTree(it) }
            .count()
            .toLong()
    }

    data class Point(val x: Int = 0, val y: Int = 0) {
        operator fun plus(increment: Slope): Point {
            return Point(x + increment.dX, y + increment.dY)
        }
    }
    data class Slope(val dX: Int, val dY: Int)
    data class Mountain(val map: List<CharArray>) {
        val width: Int
            get() = map[0].size
        val height: Int
            get() = map.size
        fun hasTree(position: Point): Boolean {
            return map[position.y][position.x % this.width] == '#'
        }
        fun trajectory(start: Point, slope: Slope): Sequence<Point> {
            val height = this.height
            return sequence {
                var toboggan = start
                for (row in 0 until height step slope.dY) {
                    yield(toboggan)
                    toboggan += slope
                }
            }
        }
    }
}
