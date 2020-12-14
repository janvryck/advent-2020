package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day14Test {

    @Test
    fun part1() {
        var solution = Day14("sample-d14").part1()
        assertThat(solution).isEqualTo(165L)
        solution = Day14().part1()
        assertThat(solution).isEqualTo(13727901897109L)
    }

    @Test
    fun part2() {
        var solution = Day14("sample-d14-2").part2()
        assertThat(solution).isEqualTo(208L)
        solution = Day14().part2()
        assertThat(solution).isEqualTo(5579916171823L)
    }

}