package be.janvanryckeghem.aoc2020

class Day14(inputFile: String = "d14") : Day(inputFile) {
    override val DAY: String = "DAY 14"
    override val parseInput: (String) -> List<String> = { input -> input.lines().filter { it.isNotBlank() } }

    private val MEMORY_INSTRUCTION = "mem\\[(\\d+)] = (\\d+)".toRegex()

    override fun solvePart1(input: String): Number {
        val initProgram = parseInput(input)
        var mask = ""
        val mem = mutableMapOf<Int, Long>()
        initProgram.forEach {
            when {
                it.startsWith("mask") -> mask = it.substringAfter("=").trim()
                it.startsWith("mem") -> {
                    val memory = MEMORY_INSTRUCTION.matchEntire(it)
                        ?: throw IllegalStateException("Could not parse instruction '$it'")
                    val idx = memory.groupValues[1].toInt()
                    val value = memory.groupValues[2].toLong()
                    mem[idx] = maskValue(value, mask)
                }
            }
        }
        return mem.values.sum()
    }

    private fun maskValue(value: Long, mask: String): Long {
        val binary = value.toString(2).padStart(36, '0').toCharArray()
        mask.mapIndexed { idx, c -> idx to c }
            .filter { it.second.isDigit() }
            .forEach { (idx, charMask) -> binary[idx] = charMask }
        return String(binary).toLong(2)
    }

    override fun solvePart2(input: String): Number {
        val initProgram = parseInput(input)
        var mask = ""
        val mem = mutableMapOf<Long, Long>()
        initProgram.forEach {
            when {
                it.startsWith("mask") -> mask = it.substringAfter("=").trim()
                it.startsWith("mem") -> {
                    val memory = MEMORY_INSTRUCTION.matchEntire(it)
                        ?: throw IllegalStateException("Could not parse instruction '$it'")
                    val idx = memory.groupValues[1].toLong()
                    val value = memory.groupValues[2].toLong()
                    for (index in maskMemory(idx, mask)) {
                        mem[index] = value
                    }
                }
            }
        }
        return mem.values.sum()
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