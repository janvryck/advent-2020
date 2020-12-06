package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day06Test {

    @Test
    fun part1() {
        val solution = Day06("sample-d06").part1()
        assertThat(solution).isEqualTo(11)
    }

    @Test
    fun part2() {
        val solution = Day06("sample-d06").part2()
        assertThat(solution).isEqualTo(6)
    }

    @Test
    fun solutions() {
        val day06 = Day06()
        assertThat(day06.part1()).isEqualTo(6416)
        assertThat(day06.part2()).isEqualTo(3050)
    }
}