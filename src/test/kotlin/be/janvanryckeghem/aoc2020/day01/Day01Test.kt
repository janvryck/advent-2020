package be.janvanryckeghem.aoc2020.day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day01Test {

    @Test
    fun sample01_part1() {
        val solution = Day01("sample-d01").part1()
        assertThat(solution).isEqualTo("514579")
    }

    @Test
    fun sample01_part2() {
        val solution = Day01("sample-d01").part2()
        assertThat(solution).isEqualTo("241861950")
    }

}