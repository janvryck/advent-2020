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
        return Program(bootCode)
            .execute()
            .accumulator
    }

    override fun solvePart2(input: String): Number {
        val bootCode = parseInput(input).toMutableList()
        return Program(bootCode)
            .fixCorruption()
            .accumulator
    }

    data class Instruction(val operation: String, val argument: Int)
    data class Program(val instructions: List<Instruction>) {
        fun fixCorruption(): Execution {
            val instructions = this.instructions.toMutableList()
            for (i in instructions.indices) {
                switchJumpAndNop(instructions, i)
                val execution = execute(instructions)
                if (execution.idx == instructions.size) {
                    return execution
                }
                switchJumpAndNop(instructions, i)
            }
            throw IllegalStateException("Could not fix program")
        }

        private fun switchJumpAndNop(instructions: MutableList<Instruction>, idx: Int) {
            when (instructions[idx].operation) {
                "jmp" -> instructions[idx] = Instruction("nop", instructions[idx].argument)
                "nop" -> instructions[idx] = Instruction("jmp", instructions[idx].argument)
            }
        }

        fun execute(instructions: List<Instruction> = this.instructions): Execution {
            val visitedInstructions = mutableListOf<Int>()
            var execution = Execution()
            while (execution.idx !in visitedInstructions && execution.idx in instructions.indices) {
                visitedInstructions.add(execution.idx)
                val instruction = instructions[execution.idx]
                execution = processInstruction(instruction, execution)
            }
            return execution
        }

        private fun processInstruction(instruction: Instruction, execution: Execution): Execution {
            var idx = execution.idx
            var accumulator = execution.accumulator
            when (instruction.operation) {
                "acc" -> {
                    accumulator += instruction.argument
                    idx++
                }
                "jmp" -> {
                    idx += instruction.argument
                }
                else -> {
                    idx++
                }
            }
            return Execution(idx, accumulator)

        }
    }

    data class Execution(val idx: Int = 0, val accumulator: Int = 0)
}