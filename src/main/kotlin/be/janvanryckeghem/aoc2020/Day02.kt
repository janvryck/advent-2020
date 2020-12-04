package be.janvanryckeghem.aoc2020

class Day02(inputFile: String = "d02"): Day(inputFile) {

    override val DAY: String = "Day 02"

    private val PATTERN = Regex("^(\\d+)-(\\d+) ([a-z]): ([a-z]+)$")

    override val parseInput: (String) -> List<Pair<Policy, String>> = { input ->
        input.lines()
            .filter { s -> s.isNotEmpty() }
            .map(toPolicyPasswordPair)
    }
    private val toPolicyPasswordPair = { s: String ->
        val res = PATTERN.matchEntire(s)!!
        val from = res.groupValues[1].toInt()
        val to = res.groupValues[2].toInt()
        val char = res.groupValues[3].toCharArray()[0]
        val password = res.groupValues[4]
        Pair(Policy(from, to, char), password)
    }

    override fun solvePart1(input: String): Number {
        return parseInput(input)
            .filter(matchesPolicy)
            .size
    }

    override fun solvePart2(input: String): Number {
        return parseInput(input)
            .filter(matchesActualPolicy)
            .size
    }

    private val matchesPolicy = { pair: Pair<Policy, String> ->
        val policy = pair.first
        val occurrences = pair.second.count { it == policy.char }
        occurrences in policy.from..policy.to
    }

    private val matchesActualPolicy = { pair: Pair<Policy, String> ->
        val policy = pair.first
        val password = pair.second

        val firstIdxMatches = password[policy.from - 1] == policy.char
        val secondIdxMatches = password[policy.to - 1] == policy.char

        firstIdxMatches.xor(secondIdxMatches)
    }

    data class Policy(val from: Int, val to: Int, val char: Char)
}