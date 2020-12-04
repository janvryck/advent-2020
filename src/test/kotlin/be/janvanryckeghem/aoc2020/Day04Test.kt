package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class Day04Test {

    @Test
    fun part1() {
        val solution = Day04("sample-d04").part1()
        Assertions.assertThat(solution).isEqualTo(2)
    }

    @Test
    fun part2_invalid() {
        val solution = Day04("sample-d04-invalid").part2()
        Assertions.assertThat(solution).isEqualTo(0)
    }

    @Test
    fun part2_valid() {
        val solution = Day04("sample-d04-valid").part2()
        Assertions.assertThat(solution).isEqualTo(4)
    }
}