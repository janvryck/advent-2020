package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day17Test {

    @Test
    fun part1() {
        var solution = Day17("sample-d17").part1()
        assertThat(solution).isEqualTo(112)
        solution = Day17().part1()
        assertThat(solution).isEqualTo(315)
    }

    @Test
    fun part2() {
        var solution = Day17("sample-d17").part2()
        assertThat(solution).isEqualTo(848)
        //solution = Day17().part2()
        //assertThat(solution).isEqualTo(848)
    }

}