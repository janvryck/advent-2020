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
            .result
    }

    override fun solvePart2(input: String): Number {
        val bootCode = parseInput(input).toMutableList()
        return Program(bootCode)
            .fixCorruption()
            .result
    }

    data class Instruction(val operation: String, val argument: Int)
    data class Program(val instructions: List<Instruction>) {

        fun fixCorruption(): Execution {
            val instructions = this.instructions.toMutableList()
            for (i in instructions.indices) {
                when(instructions[i].operation) {
                    "jmp" -> instructions[i] = Instruction("nop", instructions[i].argument)
                    "nop" -> instructions[i] = Instruction("jmp", instructions[i].argument)
                }

                val execution = execute(instructions)
                if (execution.idx == instructions.size) {
                    return execution
                }

                when(instructions[i].operation) {
                    "jmp" -> instructions[i] = Instruction("nop", instructions[i].argument)
                    "nop" -> instructions[i] = Instruction("jmp", instructions[i].argument)
                }
            }

            throw IllegalStateException("Could not fix program")
        }

        fun execute(instructions: List<Instruction> = this.instructions): Execution {
            val visitedInstructions = mutableListOf<Int>()
            var accumulator = 0
            var idx = 0
            while (idx !in visitedInstructions && idx in instructions.indices) {
                visitedInstructions.add(idx)
                val instruction = instructions[idx]
                val pair = processInstruction(instruction, accumulator, idx)
                accumulator = pair.first
                idx = pair.second
            }
            return Execution(idx, accumulator)
        }

        private fun processInstruction(instruction: Instruction, accumulator: Int, idx: Int): Pair<Int, Int> {
            var accumulator1 = accumulator
            var idx1 = idx
            when (instruction.operation) {
                "acc" -> {
                    accumulator1 += instruction.argument
                    idx1++
                }
                "jmp" -> {
                    idx1 += instruction.argument
                }
                else -> {
                    idx1++
                }
            }
            return Pair(accumulator1, idx1)
        }
    }
    data class Execution(val idx: Int, val result: Int)
}