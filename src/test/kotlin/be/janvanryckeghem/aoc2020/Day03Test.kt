package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class Day03Test {

    @Test
    fun part1() {
        val solution = Day03("sample-d03").part1()
        Assertions.assertThat(solution).isEqualTo(7L)
    }

    @Test
    fun part2() {
        val solution = Day03("sample-d03").part2()
        Assertions.assertThat(solution).isEqualTo(336L)
    }
}