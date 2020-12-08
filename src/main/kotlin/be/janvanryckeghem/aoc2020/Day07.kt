package be.janvanryckeghem.aoc2020

class Day07(inputFile: String = "d07") : Day(inputFile) {
    override val DAY: String = "DAY O7"
    override val parseInput: (String) -> List<String> = String::lines

    private val CONTAINER_REGEX = "(.*) bags? contain (.*)".toRegex()
    private val CONTENTS_REGEX = "(\\d+) (.*) bags?.?".toRegex()

    override fun solvePart1(input: String): Number {
        val rules = parseInput(input)
            .map(::parseRule)
        return Rules(rules)
            .allBagsContaining("shiny gold")
            .count()
    }

    override fun solvePart2(input: String): Number {
        val rules = Rules(
            parseInput(input)
                .map(::parseRule)
        )
        val goldenLuggage = rules.ruleFor("shiny gold")
        return rules.totalBagsContainedIn(goldenLuggage)
    }

    private fun parseRule(input: String): LuggageRule {
        val container = CONTAINER_REGEX.matchEntire(input)!!.groupValues
        val contents = container[2].split(",")
            .mapNotNull { s -> CONTENTS_REGEX.find(s) }
            .map { r -> r.groups }
            .associateBy({ groups -> groups[2]!!.value }, { groups -> groups[1]!!.value.toInt() })
        return LuggageRule(container[1], contents)
    }

    data class LuggageRule(val bagColor: String, val contents: Map<String, Int>)
    data class Rules(val rules: List<LuggageRule>) {
        private val innerLuggageItself = 1

        fun ruleFor(color: String): LuggageRule {
            return rules.filter { r -> r.bagColor == color }.first()
        }

        fun allBagsContaining(color: String): List<LuggageRule> {
            return rules.filter { rule -> containsTransitively(rule, color) }
        }

        fun totalBagsContainedIn(luggage: LuggageRule): Int {
            return luggage.contents
                .map { innerLuggage -> ruleFor(innerLuggage.key) }
                .map { innerLuggage ->
                    luggage.contents[innerLuggage.bagColor]!!
                        .times(totalBagsContainedIn(innerLuggage).plus(innerLuggageItself))
                }
                .sum()
        }

        private fun containsTransitively(rule: LuggageRule, color: String): Boolean {
            return rule.contents.containsKey("shiny gold") ||
                    rules.filter { r -> rule.contents.containsKey(r.bagColor) }
                        .any { r -> containsTransitively(r, color) }
        }
    }
}