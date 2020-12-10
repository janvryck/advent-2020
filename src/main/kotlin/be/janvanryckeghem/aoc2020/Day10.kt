package be.janvanryckeghem.aoc2020

class Day10(inputFile: String = "d10") : Day(inputFile) {
    override val DAY: String = "DAY 10"
    override val parseInput: (String) -> List<Int> = { string ->
        string.lines().map { s -> s.toInt() }.sorted()
    }

    override fun solvePart1(input: String): Number {
        var currentJolts = 0
        var differenceOfOne = 0
        var differenceOfThree = 1
        parseInput(input)
            .forEach { i ->
                when (i - currentJolts) {
                    1 -> differenceOfOne++
                    3 -> differenceOfThree++
                }
                currentJolts = i
            }
        return differenceOfOne * differenceOfThree
    }

    override fun solvePart2(input: String): Number {
        val adapters = parseInput(input).sorted()
        val device = adapters.max()!! + 3

        val joltsToVerify = adapters + device
        val matchingAdapters = joltsToVerify.associateWith { 0L }.toMutableMap()
        matchingAdapters[0] = 1L

        joltsToVerify.map { key -> combinationsFor(key, matchingAdapters) }

        return matchingAdapters[device]!!
    }

    private fun combinationsFor(key: Int, matchingAdapters: MutableMap<Int, Long>) {
        if (key != 0) {
            var matching = 0L
            for (k in key - 3 until key) {
                matching += matchingAdapters.computeIfAbsent(k) { 0L }
            }
            matchingAdapters[key] = matching
        }
    }

}