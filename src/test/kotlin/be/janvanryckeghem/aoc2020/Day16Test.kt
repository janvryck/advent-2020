package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day16Test {

    @Test
    fun part1() {
        val solution = Day16("sample-d16").part1()
        assertThat(solution).isEqualTo(71)
    }

    @Test
    fun part2() {
        val solution = Day16().part2()
        assertThat(solution).isEqualTo(5977293343129)
    }

    @Test
    fun determineFieldPositions() {
        val solution = Day16("sample-d16-2").getPuzzle().determineFieldPositions()
        assertThat(solution["row"]).isEqualTo(0)
        assertThat(solution["class"]).isEqualTo(1)
        assertThat(solution["seat"]).isEqualTo(2)
    }

}