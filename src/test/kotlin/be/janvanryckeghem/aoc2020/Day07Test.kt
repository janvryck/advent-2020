package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day07Test {
    @Test
    fun part1() {
        val solution = Day07("sample-d07").part1()
        assertThat(solution).isEqualTo(4)
    }
    @Test
    fun part2() {
        var solution = Day07("sample-d07").part2()
        assertThat(solution).isEqualTo(32)
        // Second sample:
        solution = Day07("sample-d07-2").part2()
        assertThat(solution).isEqualTo(126)
    }
}