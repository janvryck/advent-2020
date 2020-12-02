package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day01Test {

    @Test
    fun part1() {
        val solution = Day01("sample-d01").part1()
        assertThat(solution).isEqualTo(514579)
    }

    @Test
    fun part2() {
        val solution = Day01("sample-d01").part2()
        assertThat(solution).isEqualTo(241861950)
    }

}