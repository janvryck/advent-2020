package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day02Test {
    @Test
    fun part1() {
        val solution = Day02("sample-d02").part1()
        assertThat(solution).isEqualTo(2)
    }
    @Test
    fun part2() {
        val solution = Day02("sample-d02").part2()
        assertThat(solution).isEqualTo(1)
    }

}