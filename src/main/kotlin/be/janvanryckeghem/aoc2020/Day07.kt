package be.janvanryckeghem.aoc2020

class Day07(inputFile: String = "d07"): Day(inputFile) {
    override val DAY: String = "DAY O7"
    override val parseInput: (String) -> List<String> = String::lines

    private val CONTAINER_REGEX = "(.*) bags? contain (.*)".toRegex()
    private val CONTENTS_REGEX = "(\\d+) (.*) bags?.?".toRegex()

    override fun solvePart1(input: String): Number {
        val rules = parseInput(input)
            .map(::parseRule)
        return rules
            .filter { rule -> containsGold(rule, rules) }
            .count()
    }

    override fun solvePart2(input: String): Number {
        val rules = parseInput(input)
            .map(::parseRule)
        val goldenLuggage = rules.filter { r -> r.bagColor == "shiny gold" }.first()
        return nestedBagsIn(goldenLuggage, rules)
    }

    fun parseRule(input: String): LuggageRule {
        val container = CONTAINER_REGEX.matchEntire(input)!!.groupValues
        val contents = container[2].split(",")
            .map { s -> CONTENTS_REGEX.find(s) }
            .filterNotNull()
            .map { r -> r.groups}
            .associateBy( { groups -> groups[2]!!.value }, { groups -> groups[1]!!.value.toInt() } )
        return LuggageRule(container[1], contents)
    }

    fun containsGold(rule: LuggageRule, rules: List<LuggageRule>): Boolean {
        return rule.contents.containsKey("shiny gold") ||
                rules.filter { r -> rule.contents.containsKey(r.bagColor) }
                    .any { r -> containsGold(r, rules) }
    }

    fun nestedBagsIn(luggage: LuggageRule, rules: List<LuggageRule>): Int {
        return luggage.contents
            .map { rule -> rules.filter { r -> r.bagColor == rule.key }.first() }
            .map { rule -> luggage.contents[rule.bagColor]!! * (nestedBagsIn(rule, rules) + 1) }
            .sum()
    }

    data class LuggageRule(val bagColor: String, val contents: Map<String, Int>)
}