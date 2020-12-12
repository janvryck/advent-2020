package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class Day12Test {

    @Test
    fun part1() {
        var solution = Day12("sample-d12").part1()
        Assertions.assertThat(solution).isEqualTo(25)
        solution = Day12().part1()
        Assertions.assertThat(solution).isEqualTo(439)
    }

    @Test
    fun part2() {
        var solution = Day12("sample-d12").part2()
        Assertions.assertThat(solution).isEqualTo(286)
        solution = Day12().part2()
        Assertions.assertThat(solution).isEqualTo(12385)
    }
}