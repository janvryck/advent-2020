package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day09Test {

    @Test
    fun xmasEncryptionViolation() {
        val day09 = Day09("sample-d09")
        val solution = day09.xmasViolation(day09.parseInput(day09.input), 5)
        assertThat(solution).isEqualTo(127)
    }

    @Test
    fun xmasWeakness() {
        val day09 = Day09("sample-d09")
        val solution = day09.xmasWeakness(day09.parseInput(day09.input), 127)
        assertThat(solution).isEqualTo(62)
    }

}