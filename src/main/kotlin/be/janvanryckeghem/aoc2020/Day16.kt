package be.janvanryckeghem.aoc2020

class Day16(inputFile: String = "d16") : Day(inputFile) {
    override val DAY: String = "DAY 16"
    override val parseInput: (String) -> Puzzle = { input ->
        val inputLines = input.lines().filter(String::isNotBlank)
        val myTicketIdx = inputLines.indexOf("your ticket:")

        val rules = inputLines.subList(0, myTicketIdx).map(::toRule)
        val myTicket = toTicket(inputLines[myTicketIdx + 1])
        val otherTickets = inputLines.slice(myTicketIdx + 3 until inputLines.size).map(::toTicket)

        Puzzle(rules, myTicket, otherTickets)
    }

    override fun solvePart1(input: String): Number {
        val puzzle = parseInput(input)
        return puzzle.otherTickets.map { it.validate(puzzle.rules) }.sum()
    }

    override fun solvePart2(input: String): Number {
        val puzzle = parseInput(input)
        val determineFieldPositions = puzzle.determineFieldPositions()
        return determineFieldPositions
            .filter { it.key.startsWith("departure") }
            .map { puzzle.ticket.fields[it.value].toLong() }
            .reduce(Long::times)
    }

    fun getPuzzle(): Puzzle {
        return parseInput(input)
    }

    data class Puzzle(val rules: List<Rule>, val ticket: Ticket, var otherTickets: List<Ticket>) {
        private val validTickets: List<Ticket>
                get() = otherTickets.filter { it.validate(rules) == 0 }

        fun determineFieldPositions(): Map<String, Int> {
            var availableRules = mutableMapOf<Int, List<Rule>>()
            for (i in validTickets[0].fields.indices) {
                val elements: List<Int> = validTickets.map { t -> t.fields[i] }
                availableRules[i] = rules.filter { r ->
                    elements.all { r.ranges.any { range -> range.contains(it)} }
                }
            }

            val ruleIndices = mutableMapOf<String, Int>()
            while(ruleIndices.size < rules.size) {
                val (idx, rules) = availableRules.filter { it.value.size == 1 }.entries.first()
                ruleIndices[rules.first().name] = idx
                availableRules = availableRules
                    .mapValues { (_, value) -> value.filter { it.name != rules.first().name } }
                    .toMutableMap()
            }
            return ruleIndices
        }
    }

    data class Rule(val name: String, val ranges: List<IntRange>) {
        fun isValid(field: Int): Boolean {
            return ranges.any { it.contains(field) }
        }
    }

    data class Ticket(val fields: List<Int>) {
        fun validate(rules: List<Rule>): Int {
            return fields.filter { field -> rules.none { it.isValid(field) } }.sum()
        }
    }

    companion object {
        fun toRule(input: String): Rule {
            val name = input.substringBefore(':')
            val ranges = input.substringAfter(':').trim().split(" or ")
                .map { it.split('-') }
                .map { it[0].toInt()..it[1].toInt() }
            return Rule(name, ranges)
        }

        fun toTicket(input: String): Ticket = Ticket(input.split(",").map(String::toInt))
    }
}