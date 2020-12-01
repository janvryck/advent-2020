package be.janvanryckeghem.aoc2020.day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day01Test {

    @Test
    fun sample01() {
        val solution = Day01("sample-d01").solve()

        assertThat(solution).isEqualTo("514579")
    }

}