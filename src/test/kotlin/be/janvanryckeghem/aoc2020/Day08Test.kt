package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day08Test {
    @Test
    fun part1() {
        val solution = Day08("sample-d08").part1()
        assertThat(solution).isEqualTo(5)
    }
    @Test
    fun part2() {
        val solution = Day08("sample-d08").part2()
        assertThat(solution).isEqualTo(8)
    }
}