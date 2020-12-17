package be.janvanryckeghem.aoc2020

class Day17(inputFile: String = "d17") : Day(inputFile) {
    override val DAY: String = "DAY 17"
    override val parseInput: (String) -> Set<Coordinate> = { input ->
        input.lines().map(String::toCharArray).mapIndexed { y, line ->
            line.mapIndexed { x, c -> Coordinate(x, y) to toState(c) }
        }.flatten()
            .filter { it.second == State.ACTIVE }
            .map { it.first }
            .toSet()
    }

    override fun solvePart1(input: String): Number {
        var activePoints = parseInput(input)

        repeat(6) {
            val remainingCubes = activePoints.filter { activeNeighbours(activePoints, it) in 2..3 }.toMutableList()
            val additionalCubes = activePoints.flatMap{ it.neighbours() }
                .filter { activeNeighbours(activePoints, it) == 3 }
            activePoints = (remainingCubes + additionalCubes).toSet()
        }

        return activePoints.count()
    }

    override fun solvePart2(input: String): Number {
        var activePoints = parseInput(input)

        repeat(6) {
            val remainingCubes = activePoints.filter { activeNeighbours(activePoints, it, true) in 2..3 }.toMutableList()
            val additionalCubes = activePoints.flatMap{ it.neighbours(true) }
                .filter { activeNeighbours(activePoints, it, true) == 3 }
            activePoints = (remainingCubes + additionalCubes).toSet()
        }

        return activePoints.count()
    }

    private fun activeNeighbours(activePoints: Set<Coordinate>, point: Coordinate, is4D: Boolean = false): Int {
        return point.neighbours(is4D).filter(activePoints::contains).size
    }

    data class Coordinate(val x: Int, val y: Int, val z: Int = 0, val w: Int = 0) {
        fun neighbours(is4D: Boolean = false): Set<Coordinate> {
            val neighbours = mutableListOf<Coordinate>()
            for (dx in -1..1) {
                for (dy in -1..1) {
                    for (dz in -1..1) {
                        for(dw in if (is4D) -1..1 else 0..0) {
                            if (!(dx == 0 && dy == 0 && dz == 0 && dw == 0))
                                neighbours.add(Coordinate(x + dx, y + dy, z + dz, w + dw))
                        }
                    }
                }
            }
            return neighbours.toSet()
        }
    }

    enum class State {
        ACTIVE,
        INACTIVE;
    }

    companion object {
        fun toState(c: Char): State {
            return when (c) {
                '.' -> State.INACTIVE
                '#' -> State.ACTIVE
                else -> throw IllegalArgumentException("Can't parse input '$c' to State.")
            }
        }
    }
}