package be.janvanryckeghem.aoc2020

import kotlin.math.abs

class Day13(inputFile: String = "d13") : Day(inputFile) {
    override val DAY: String = "DAY 13"
    override val parseInput: (String) -> List<String> = { it.lines() }

    override fun solvePart1(input: String): Number {
        val parsed = parseInput(input)
        val arrival = parsed[0].toInt()
        val buslines = parsed[1].split(",")
            .filter { it != "x" }
            .map { it.toInt() }

        return buslines.sortedBy { it - arrival.rem(it) }
            .map { it * (it - arrival.rem(it)) }
            .first()
    }

    override fun solvePart2(input: String): Number {
        val buslines = parseInput(input)[1].split(",")
        return consecutiveDeparturesFor(buslines)
    }

    fun consecutiveDeparturesFor(buslines: List<String>): Long {
        val buses: List<Long> = buslines
            .map { it.toLongOrNull() ?: 1 }

        var increment = buses.first()
        var departure = increment
        var idx = 1
        while (idx < buses.size) {
            if ((departure + idx).rem(buses[idx]) == 0L) {
                increment *= buses[idx]
                idx++
            } else {
                departure += increment
            }
        }
        return departure
    }
}