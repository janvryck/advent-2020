package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun part1() {
        var solution = Day13("sample-d13").part1()
        assertThat(solution).isEqualTo(295)
        solution = Day13().part1()
        assertThat(solution).isEqualTo(161)
    }

    @Test
    fun part2() {
        var solution = Day13("sample-d13").part2()
        assertThat(solution).isEqualTo(1068781L)
        solution = Day13().part2()
        assertThat(solution).isEqualTo(213890632230818L)
    }

    @Test
    fun consecutiveDepartures() {
        val day13 = Day13()
        var solution = day13.consecutiveDeparturesFor("17,x,13,19".split(","))
        assertThat(solution).isEqualTo(3417L)
        solution = day13.consecutiveDeparturesFor("67,7,59,61".split(","))
        assertThat(solution).isEqualTo(754018L)
        solution = day13.consecutiveDeparturesFor("67,x,7,59,61".split(","))
        assertThat(solution).isEqualTo(779210L)
        solution = day13.consecutiveDeparturesFor("67,7,x,59,61".split(","))
        assertThat(solution).isEqualTo(1261476L)
        solution = day13.consecutiveDeparturesFor("1789,37,47,1889".split(","))
        assertThat(solution).isEqualTo(1202161486L)
    }
}