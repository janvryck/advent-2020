package be.janvanryckeghem.aoc2020

class Day18(inputFile: String = "d18") : Day(inputFile) {
    override val DAY: String = "DAY 18"
    override val parseInput: (String) -> List<String> = { input ->
        input.lines().map { it.replace(" ", "") }
    }

    private val parenthesesExpression = "\\(([^()])+\\)".toRegex()
    private val parentheses = "[()]".toRegex()

    private val additionExpression = "\\d+\\+\\d+".toRegex()
    private val addition = "\\+".toRegex()

    override fun solvePart1(input: String): Number {
        return parseInput(input)
            .map { solve(it) }
            .sum()
    }

    override fun solvePart2(input: String): Number {
        return parseInput(input)
            .map { solve(it, true) }
            .sum()
    }

    fun solve(expression: String, reversedPrecedence: Boolean = false): Long {
        return when {
            expression.contains(parentheses) ->
                solveParentheses(expression, reversedPrecedence)
            expression.contains(addition) && reversedPrecedence ->
                solveAddition(expression, reversedPrecedence)
            else ->
                solveWithoutPrecedence(expression)
        }
    }

    private fun solveAddition(expression: String, reversedPrecedence: Boolean): Long {
        val evaluateAddition = expression.replace(additionExpression) {
            solveWithoutPrecedence(it.value).toString()
        }
        return solve(evaluateAddition, reversedPrecedence)
    }

    private fun solveParentheses(expression: String, reversedPrecedence: Boolean): Long {
        val evaluateParentheses = expression.replace(parenthesesExpression) {
            solve(it.value.drop(1).dropLast(1), reversedPrecedence).toString()
        }
        return solve(evaluateParentheses, reversedPrecedence)
    }

    private fun solveWithoutPrecedence(expression: String): Long {
        val numbers = expression.split("[+*]".toRegex()).map(String::toLong)
        val operations = expression.split("\\d+".toRegex()).filterNot(String::isBlank)

        var solution = numbers[0]
        operations.forEachIndexed { index, operation ->
            when (operation) {
                "+" -> solution += numbers[index + 1]
                "*" -> solution *= numbers[index + 1]
            }
        }
        return solution
    }

}