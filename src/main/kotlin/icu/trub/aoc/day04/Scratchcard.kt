package icu.trub.aoc.day04

data class Scratchcard(val id: Int, val winningNumbers: Set<Int>, val actualNumbers: Set<Int>) {
    companion object {
        fun parse(line: String): Scratchcard {
            val (idStr, winningStr, actualStr) = line.split(":", "|")
            return Scratchcard(
                idStr.split(" ").last().toInt(),
                parseToIntSet(winningStr),
                parseToIntSet(actualStr)
            )
        }

        private fun parseToIntSet(values: String) = values.split(" ")
            .filter(String::isNotBlank)
            .map(String::toInt)
            .toSet()
    }

    val points: Int
        get() {
            var result = 0
            for (actualNumber in actualNumbers) {
                if (actualNumber in winningNumbers) {
                    result = when (result) {
                        0 -> 1
                        else -> result * 2
                    }
                }
            }
            return result
        }

    val matchingNumbers: Set<Int>
        get() {
            return actualNumbers.filter { n -> winningNumbers.contains(n) }.toSet()
        }

}