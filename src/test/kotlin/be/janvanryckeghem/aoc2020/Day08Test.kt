package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day08Test {

    @Test
    fun part1() {
        var solution = Day08("sample-d08").part1()
        assertThat(solution).isEqualTo(5)
        solution = Day08().part1()
        assertThat(solution).isEqualTo(1671)
    }

    @Test
    fun part2() {
        var solution = Day08("sample-d08").part2()
        assertThat(solution).isEqualTo(8)
        solution = Day08().part2()
        assertThat(solution).isEqualTo(892)
    }
}