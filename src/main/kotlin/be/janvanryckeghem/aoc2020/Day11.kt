package be.janvanryckeghem.aoc2020

class Day11(inputFile: String = "d11") : Day(inputFile) {
    override val DAY: String = "DAY 11"
    override val parseInput: (String) -> Map<SeatingLocation, SeatingState> = { input ->
        val list = input.lines().map { it.toCharArray() }
        list.mapIndexed { i, chars -> chars.mapIndexed { j, _ -> SeatingLocation(i, j) } }
            .flatten()
            .associateBy({ it }, { fromChar(list[it.x][it.y]) })
    }

    override fun solvePart1(input: String): Number {
        var floorplan = Floorplan(parseInput(input))

        var occupation: Pair<Number?, Number> = null to floorplan.occupation
        while (occupation.first != occupation.second) {
            floorplan = floorplan.iterate()
            occupation = occupation.second to floorplan.occupation
        }

        return occupation.second
    }

    override fun solvePart2(input: String): Number {
        var floorplan = Floorplan(parseInput(input))

        var occupation: Pair<Number?, Number> = null to floorplan.occupation
        while (occupation.first != occupation.second) {
            floorplan = floorplan.tolerant()
            occupation = occupation.second to floorplan.occupation
        }

        return occupation.second
    }

    data class Floorplan(val plan: Map<SeatingLocation, SeatingState>) {
        val occupation = plan.values.count { it == SeatingState.OCCUPIED }
        private val maxX = plan.keys.maxBy { it.x }!!.x
        private val maxY = plan.keys.maxBy { it.y }!!.y

        fun iterate(): Floorplan {
            return Floorplan(
                plan.keys.associateBy({ it }, { iterationFor(it) })
            )
        }

        fun tolerant(): Floorplan {
            return Floorplan(
                plan.keys.associateBy({ it }, { tolerantIterationFor(it) })
            )
        }

        private fun iterationFor(location: SeatingLocation): SeatingState {
            val occupationOfAdjacentSeats = location.adjacentLocations()
                .map { plan.getOrDefault(it, SeatingState.EMPTY) }
                .count { it == SeatingState.OCCUPIED }

            return when {
                plan[location] == SeatingState.EMPTY && occupationOfAdjacentSeats == 0 -> SeatingState.OCCUPIED
                plan[location] == SeatingState.OCCUPIED && occupationOfAdjacentSeats >= 4 -> SeatingState.EMPTY
                else -> plan[location] ?: SeatingState.FLOOR
            }
        }

        private fun tolerantIterationFor(location: SeatingLocation): SeatingState {
            val visibleSeats: List<SeatingState> = visibleSeatsFor(location)
            val occupationOfVisibleSeats = visibleSeats.count { it == SeatingState.OCCUPIED }

            return when {
                plan[location] == SeatingState.EMPTY && occupationOfVisibleSeats == 0 -> SeatingState.OCCUPIED
                plan[location] == SeatingState.OCCUPIED && occupationOfVisibleSeats >= 5 -> SeatingState.EMPTY
                else -> plan[location] ?: SeatingState.FLOOR
            }
        }

        private fun visibleSeatsFor(location: SeatingLocation): List<SeatingState> {
            return allDirections().mapNotNull { direction -> seatingInDirection(location, direction) }
        }

        private fun seatingInDirection(location: SeatingLocation, direction: Pair<Int, Int>): SeatingState? {
            var loc = location + direction
            var seat: SeatingState? = null
            while (loc.x in 0..maxX && loc.y in 0..maxY) {
                if (plan[loc] != SeatingState.FLOOR) {
                    seat = plan[loc]
                    break
                }
                loc += direction
            }
            return seat
        }
    }

    data class SeatingLocation(val x: Int, val y: Int) {
        fun adjacentLocations(): List<SeatingLocation> = allDirections().map { this + it }

        operator fun plus(increment: Pair<Int, Int>): SeatingLocation {
            return SeatingLocation(x + increment.first, y + increment.second)
        }
    }

    enum class SeatingState {
        OCCUPIED,
        EMPTY,
        FLOOR;
    }

    companion object {
        fun fromChar(i: Char): SeatingState {
            return when (i) {
                'L' -> SeatingState.EMPTY
                '#' -> SeatingState.OCCUPIED
                '.' -> SeatingState.FLOOR
                else -> throw IllegalStateException("Could not parse seating for '$i'")
            }
        }

        fun allDirections(): List<Pair<Int, Int>> {
            return (-1..1).flatMap { dx ->
                (-1..1).mapNotNull { dy ->
                    if (dx == 0 && dy == 0) null
                    else dx to dy
                }
            }
        }
    }
}