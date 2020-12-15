package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day15Test {

    @Test
    fun part1() {
        var solution = Day15("sample-d15").part1()
        assertThat(solution).isEqualTo(436)
    }

    @Test
    fun part2() {
        var solution = Day15("sample-d15").part2()
        assertThat(solution).isEqualTo(175594)
    }

    @Test
    fun memoryGame() {
        val day15 = Day15()
        var solution = day15.memoryGame(listOf(1, 3, 2))
        assertThat(solution).isEqualTo(1)
        solution = day15.memoryGame(listOf(2, 1, 3))
        assertThat(solution).isEqualTo(10)
        solution = day15.memoryGame(listOf(1, 2, 3))
        assertThat(solution).isEqualTo(27)
        solution = day15.memoryGame(listOf(2, 3, 1))
        assertThat(solution).isEqualTo(78)
        solution = day15.memoryGame(listOf(3, 2, 1))
        assertThat(solution).isEqualTo(438)
        solution = day15.memoryGame(listOf(3, 1, 2))
        assertThat(solution).isEqualTo(1836)
        /* -- UNOPTIMIZED, LONG-RUNNING --
        solution = day15.memoryGame(listOf(1, 3, 2), 30_000_000)
        assertThat(solution).isEqualTo(2578)
        solution = day15.memoryGame(listOf(2, 1, 3), 30_000_000)
        assertThat(solution).isEqualTo(3544142)
        solution = day15.memoryGame(listOf(1, 2, 3), 30_000_000)
        assertThat(solution).isEqualTo(261214)
        solution = day15.memoryGame(listOf(2, 3, 1), 30_000_000)
        assertThat(solution).isEqualTo(6895259)
        solution = day15.memoryGame(listOf(3, 2, 1), 30_000_000)
        assertThat(solution).isEqualTo(18)
        solution = day15.memoryGame(listOf(3, 1, 2), 30_000_000)
        assertThat(solution).isEqualTo(362)
        */
    }

}