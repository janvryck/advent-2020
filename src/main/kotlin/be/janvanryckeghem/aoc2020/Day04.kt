package be.janvanryckeghem.aoc2020

class Day04(inputFile: String = "d04") : Day(inputFile) {
    override val DAY: String = "DAY 04"

    override val parseInput: (String) -> List<String> = { input ->
        input.split("\n\n")
    }

    override fun solvePart1(input: String): Number {
        return parseInput(input)
            .map(::Passport)
            .filter(Passport::hasRequiredFields)
            .size
    }

    override fun solvePart2(input: String): Number {
        return parseInput(input)
            .map(::Passport)
            .filter(Passport::isValid)
            .size
    }

    class Passport(raw: String) {

        private val requiredPassportFields: Map<String, (String) -> Boolean> = mapOf(
            "byr" to { s: String -> s.toInt() in 1920..2002 },
            "iyr" to { s: String -> s.toInt() in 2010..2020 },
            "eyr" to { s: String -> s.toInt() in 2020..2030 },
            "hgt" to { s: String ->
                (s.endsWith("cm") && s.dropLast(2).toInt() in 150..193) ||
                        (s.endsWith("in") && s.dropLast(2).toInt() in 59..76)
            },
            "hcl" to { s: String -> s.matches("^#[a-f0-9]{6}".toRegex()) },
            "ecl" to { s: String -> s in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
            "pid" to { s: String -> s.matches("\\d{9}".toRegex()) }
        )
        private val parsed: Map<String, String>
        private val parsePassport = { raw: String ->
            val safeGet: (Int) -> ((List<String>) -> String) = { idx -> { ls -> ls.getOrElse(idx) { "" } } }
            raw.split("\\s".toRegex())
                .map { s -> s.split(':')}
                .associateBy(safeGet(0), safeGet(1))
        }

        init {
            parsed = parsePassport(raw)
        }

        fun hasRequiredFields(): Boolean {
            return parsed.keys.containsAll(requiredPassportFields.keys)
        }

        fun isValid(): Boolean {
            return this.hasRequiredFields() && parsed.all { entry: Map.Entry<String, String> ->
                requiredPassportFields[entry.key]?.invoke(entry.value) == true || entry.key == "cid"
            }
        }
    }
}
