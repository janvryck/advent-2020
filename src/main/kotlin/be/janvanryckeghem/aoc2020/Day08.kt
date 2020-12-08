package be.janvanryckeghem.aoc2020

class Day08(inputFile: String = "d08") : Day(inputFile) {
    override val DAY: String = "DAY O8"
    override val parseInput: (String) -> List<Instruction> = { input ->
        input.lines()
            .map { s -> s.split(" ") }
            .map { split -> Instruction(split[0], split[1].toInt()) }
    }

    override fun solvePart1(input: String): Number {
        val bootCode = parseInput(input)
        val visitedInstructions = mutableListOf<Int>()
        var accumulator = 0
        var idx = 0
        while(idx !in visitedInstructions) {
            visitedInstructions.add(idx)
            when(bootCode[idx].operation) {
                "acc" -> {
                    accumulator += bootCode[idx].argument
                    idx++
                }
                "jmp" -> {
                    idx += bootCode[idx].argument
                }
                else -> {
                    idx++
                }
            }
        }
        return accumulator
    }

    override fun solvePart2(input: String): Number {
        val bootCode = parseInput(input).toMutableList()
        for (i in bootCode.indices) {
            when(bootCode[i].operation) {
                "jmp" -> bootCode[i] = Instruction("nop", bootCode[i].argument)
                "nop" -> bootCode[i] = Instruction("jmp", bootCode[i].argument)
            }
            val visitedInstructions = mutableListOf<Int>()
            var accumulator = 0
            var idx = 0
            while (idx !in visitedInstructions) {
                visitedInstructions.add(idx)
                when (bootCode[idx].operation) {
                    "acc" -> {
                        accumulator += bootCode[idx].argument
                        idx++
                    }
                    "jmp" -> {
                        idx += bootCode[idx].argument
                    }
                    else -> {
                        idx++
                    }
                }
                if (idx == bootCode.size) return accumulator
            }
            when(bootCode[i].operation) {
                "jmp" -> bootCode[i] = Instruction("nop", bootCode[i].argument)
                "nop" -> bootCode[i] = Instruction("jmp", bootCode[i].argument)
            }
        }
        throw IllegalStateException("No solution found")
    }

    data class Instruction(val operation: String, val argument: Int)
}