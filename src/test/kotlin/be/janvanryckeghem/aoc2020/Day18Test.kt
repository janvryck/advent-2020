package be.janvanryckeghem.aoc2020

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day18Test {

    @Test
    fun part1() {
        assertThat(Day18("sample-d18").part1()).isEqualTo(26457L)
        assertThat(Day18().part1()).isEqualTo(50_956_598_240_016L)
    }

    @Test
    fun part2() {
        assertThat(Day18("sample-d18").part2()).isEqualTo(694_173L)
    }

    @Test
    fun solveExpression() {
        val day18 = Day18()
         assertThat(day18.solve("1+2*3+4*5+6")).isEqualTo(71L)
        assertThat(day18.solve("1+(2*3)+(4*(5+6))")).isEqualTo(51L)
        assertThat(day18.solve("2*3+(4*5)")).isEqualTo(26L)
        assertThat(day18.solve("5+(8*3+9+3*4*3)")).isEqualTo(437L)
        assertThat(day18.solve("5*9*(7*3*3+9*3+(8+6*4))")).isEqualTo(12_240L)
        assertThat(day18.solve("((2+4*9)*(6+9*8+6)+6)+2+4*2")).isEqualTo(13_632L)
        // PART 2
        assertThat(day18.solve("1+2*3+4*5+6", true)).isEqualTo(231L)
        assertThat(day18.solve("1+(2*3)+(4*(5+6))", true)).isEqualTo(51L)
        assertThat(day18.solve("2*3+(4*5)", true)).isEqualTo(46L)
        assertThat(day18.solve("5+(8*3+9+3*4*3)", true)).isEqualTo(1445L)
        assertThat(day18.solve("5*9*(7*3*3+9*3+(8+6*4))", true)).isEqualTo(669_060L)
        assertThat(day18.solve("((2+4*9)*(6+9*8+6)+6)+2+4*2", true)).isEqualTo(23_340L)
    }

}