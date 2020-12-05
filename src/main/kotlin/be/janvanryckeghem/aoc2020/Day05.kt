package be.janvanryckeghem.aoc2020

class Day05(inputFile: String = "d05") : Day(inputFile) {
    override val DAY: String = "DAY 05"
    override val parseInput: (String) -> List<Int> = { s -> s.lines().map(::toSeatId) }

    private val sumOfConsecutiveNrs: (Int) -> Int = { i -> (0.5*i*(i+1)).toInt() }

    override fun solvePart1(input: String): Number {
        return parseInput(input)
            .max()!!
    }

    override fun solvePart2(input: String): Number {
        val seats = parseInput(input)
        return missingNumberInConsecutiveList(seats)
    }

    fun toSeatId(seat: String): Int {
        return seat.replace("[FL]".toRegex(), "0")
            .replace("[BR]".toRegex(), "1")
            .toInt(2)
    }

    fun missingNumberInConsecutiveList(numbers: List<Int>): Int {
        val sorted = numbers.sorted()
        val expectedSumOfIds = sumOfConsecutiveNrs(sorted.last()) - sumOfConsecutiveNrs(sorted.first() - 1)
        return expectedSumOfIds - sorted.sum()
    }
}