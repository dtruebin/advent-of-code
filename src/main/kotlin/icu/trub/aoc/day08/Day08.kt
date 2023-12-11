package icu.trub.aoc.day08

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day08(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solvePartOne(): Int = with(AocUtil.readTxtResource(inputFileName).toList()) {
        Network.parse(subList(2, size))
            .navigate("AAA", "ZZZ", first())
            .count()
    }

    override fun solvePartTwo(): Int? = null // TODO Not yet implemented
}