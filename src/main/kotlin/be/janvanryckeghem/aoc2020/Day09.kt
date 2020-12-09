package be.janvanryckeghem.aoc2020

class Day09(inputFile: String = "d09") : Day(inputFile) {
    override val DAY: String = "DAY 09"
    override val parseInput: (String) -> List<Long> = { input ->
        input.lines().map { s -> s.toLong() }
    }

    override fun solvePart1(input: String): Number {
        return xmasViolation(parseInput(input))
    }

    override fun solvePart2(input: String): Number {
        val numbers = parseInput(input)
        val target = xmasViolation(numbers)
        return xmasWeakness(numbers, target)
    }

    fun xmasViolation(numbers: List<Long>, preamble: Int = 25): Long {
        for(i in preamble until numbers.size) {
            val preambleNrs = numbers.subList(i - preamble, i)
            if (!sums(preambleNrs).contains(numbers[i])) {
                return numbers[i]
            }
        }
        throw IllegalStateException("Could not find violation of XMAS rules")
    }

    fun xmasWeakness(numbers: List<Long>, target: Long): Long {
        val maxIndex = numbers.indexOf(target)
        var idx = 0
        while(idx < maxIndex) {
            var sum = numbers[idx]
            var i = idx+1
            while(sum < target && i < maxIndex) {
                sum += numbers[i]
                if (sum == target) {
                    val targetRange = numbers.subList(idx, i+1)
                    return targetRange.min()!! + targetRange.max()!!
                }
                i++
            }
            idx++
        }
        throw IllegalStateException("Could not find XMAS weakness")
    }

    private fun sums(numbers: List<Long>): List<Long> {
        val sums = mutableListOf<Long>()
        for (i in numbers.indices) {
            for (j in i+1 until numbers.size) {
                sums.add(numbers[i] + numbers[j])
            }
        }
        return sums
    }
}