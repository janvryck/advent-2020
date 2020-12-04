package be.janvanryckeghem.aoc2020

class Day04(inputFile: String = "d04") : Day(inputFile) {
    override val DAY: String = "DAY 04"

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

    override val parseInput: (String) -> List<String> = { input ->
        input.split("\n\n")
    }

    override fun solvePart1(input: String): Number {
        val passports = parseInput(input)
        var validPassports = 0
        for (passport in passports) {
            val parsedPassport = passport.split("\\s".toRegex())
                .associateBy({ s -> s.split(":")[0] }, { s -> if (s.contains(':')) s.split(":")[1] })
            if (parsedPassport.keys.containsAll(requiredPassportFields.keys)) validPassports++
        }
        return validPassports
    }

    override fun solvePart2(input: String): Number {
        val passports = parseInput(input)
        var validPassports = 0
        for (passport in passports) {
            val parsedPassport: Map<String, String> = passport.split("\\s".toRegex())
                .associateBy({ s -> s.split(":")[0] }, { s -> if (s.contains(':')) s.split(":")[1] else "" })
            if (parsedPassport.keys.containsAll(requiredPassportFields.keys)) {
                if (parsedPassport.all { entry: Map.Entry<String, String> ->
                        requiredPassportFields[entry.key]?.invoke(entry.value) == true || entry.key == "cid"
                    }) validPassports++
            }
        }
        return validPassports
    }
}