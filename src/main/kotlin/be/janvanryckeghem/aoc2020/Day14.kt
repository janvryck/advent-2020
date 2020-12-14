package be.janvanryckeghem.aoc2020

class Day14(inputFile: String = "d14") : Day(inputFile) {
    override val DAY: String = "DAY 14"

    private val memoryInstruction = "mem\\[(\\d+)] = (\\d+)".toRegex()
    override val parseInput: (String) -> List<Instruction> = { input ->
        input.lines().filter { it.isNotBlank() }.map {
            when {
                it.startsWith("mask") -> Instruction.Mask(it.substringAfter("=").trim())
                it.startsWith("mem") -> {
                    val memory = memoryInstruction.matchEntire(it)
                        ?: throw IllegalStateException("Could not parse instruction: '$it'")
                    val idx = memory.groupValues[1].toLong()
                    val value = memory.groupValues[2].toLong()
                    Instruction.Mem(idx, value)
                }
                else -> throw IllegalStateException("Unknown instruction: '$it'")
            }
        }
    }

    override fun solvePart1(input: String): Number {
        return InitializationProgram(parseInput(input)).run()
    }

    override fun solvePart2(input: String): Number {
        return InitializationProgram(parseInput(input), DecoderVersion.TWO).run()
    }

    data class InitializationProgram(
        val instructions: List<Instruction>,
        val decoderVersion: DecoderVersion = DecoderVersion.ONE
    ) {
        var mask = ""
        var memory = mutableMapOf<Long, Long>()

        fun run(): Long {
            instructions.forEach {
                val (mask, memory) = it.execute(mask, memory, decoderVersion)
                this.mask = mask
                this.memory = memory
            }
            return memory.values.sum()
        }
    }

    sealed class Instruction {
        abstract fun execute(
            mask: String,
            memory: MutableMap<Long, Long>,
            decoderVersion: DecoderVersion
        ): Pair<String, MutableMap<Long, Long>>

        class Mask(private val pattern: String) : Instruction() {
            override fun execute(
                mask: String,
                memory: MutableMap<Long, Long>,
                decoderVersion: DecoderVersion
            ): Pair<String, MutableMap<Long, Long>> {
                return pattern to memory
            }
        }

        class Mem(private val index: Long, private val value: Long) : Instruction() {
            override fun execute(
                mask: String,
                memory: MutableMap<Long, Long>,
                decoderVersion: DecoderVersion
            ): Pair<String, MutableMap<Long, Long>> {
                when (decoderVersion) {
                    DecoderVersion.ONE -> memory[index] = maskValue(value, mask)
                    DecoderVersion.TWO ->
                        for (idx in maskMemory(index, mask)) {
                            memory[idx] = value
                        }
                }
                return mask to memory
            }

            private fun maskValue(value: Long, mask: String): Long {
                val binary = value.toString(2).padStart(36, '0').toCharArray()
                mask.mapIndexed { idx, c -> idx to c }
                    .filter { it.second.isDigit() }
                    .forEach { (idx, charMask) -> binary[idx] = charMask }
                return String(binary).toLong(2)
            }

            private fun maskMemory(address: Long, mask: String): List<Long> {
                val binary = address.toString(2).padStart(36, '0').toCharArray()
                mask.mapIndexed { idx, c -> idx to c }
                    .filter { it.second in listOf('X', '1') }
                    .forEach { (idx, charMask) -> binary[idx] = charMask }

                val maskFloatingBits = maskFloatingBits(String(binary))
                return maskFloatingBits.map { it.toLong(2) }
            }

            private fun maskFloatingBits(address: String): List<String> {
                if (!address.contains('X'))
                    return listOf(address)

                return maskFloatingBits(address.replaceFirst('X', '1')) +
                        maskFloatingBits(address.replaceFirst('X', '0'))
            }
        }
    }

    enum class DecoderVersion {
        ONE,
        TWO;
    }
}