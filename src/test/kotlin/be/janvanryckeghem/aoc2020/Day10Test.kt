package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day10Test {

    @Test
    fun part1() {
        var solution = Day10("sample-d10").part1()
        assertThat(solution).isEqualTo(35)
        solution = Day10("sample-d10-2").part1()
        assertThat(solution).isEqualTo(220)
    }

    @Test
    fun part2() {
        var solution = Day10("sample-d10").part2()
        assertThat(solution).isEqualTo(8L)
        solution = Day10("sample-d10-2").part2()
        assertThat(solution).isEqualTo(19208L)
        solution = Day10().part2()
        assertThat(solution).isEqualTo(16198260678656L)
    }
}