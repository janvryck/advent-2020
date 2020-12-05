package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day05Test {

    @Test
    fun toSeatId() {
        assertThat(Day05().toSeatId("FBFBBFFRLR")).isEqualTo(357)
        assertThat(Day05().toSeatId("BFFFBBFRRR")).isEqualTo(567)
        assertThat(Day05().toSeatId("FFFBBBFRRR")).isEqualTo(119)
        assertThat(Day05().toSeatId("BBFFBBFRLL")).isEqualTo(820)
    }

    @Test
    fun part1() {
        val solution = Day05("sample-d05").part1()
        assertThat(solution).isEqualTo(820)
    }

    @Test
    fun missingNumber() {
        val day05 = Day05()
        assertThat(day05.missingNumberInConsecutiveList(listOf(1,2,3,5))).isEqualTo(4)
        assertThat(day05.missingNumberInConsecutiveList(listOf(5,3,2,1))).isEqualTo(4)
        assertThat(day05.missingNumberInConsecutiveList(listOf(1,5,3,2))).isEqualTo(4)
        assertThat(day05.missingNumberInConsecutiveList(listOf(10,12,13,14))).isEqualTo(11)
        assertThat(day05.missingNumberInConsecutiveList(listOf(15,10,12,13,14))).isEqualTo(11)
    }

}