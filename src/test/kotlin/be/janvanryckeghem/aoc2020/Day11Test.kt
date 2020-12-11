package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day11Test{

    @Test
    fun part1() {
        var solution = Day11("sample-d11").part1()
        assertThat(solution).isEqualTo(37)
        solution = Day11().part1()
        assertThat(solution).isEqualTo(2418)
    }

    @Test
    fun part2() {
        var solution = Day11("sample-d11").part2()
        assertThat(solution).isEqualTo(26)
        solution = Day11().part2()
        assertThat(solution).isEqualTo(2144)
    }
}