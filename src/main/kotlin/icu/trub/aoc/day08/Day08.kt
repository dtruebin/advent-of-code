package icu.trub.aoc.day08

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day08(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solvePartOne(): Int = with(AocUtil.readTxtResource(inputFileName).toList()) {
        Network.parse(subList(2, size))
            .navigate("AAA", "ZZZ", first())
            .toInt()
    }

    override fun solvePartTwo(): Int = with(AocUtil.readTxtResource(inputFileName).toList()) {
        Network.parse(subList(2, size))
            .navigate("A", "Z", first())
            .also { if (it > Int.MAX_VALUE) println("Day 8, Part Two - actual solution (before conversion to Int): $it") }
            .toInt()
    }
}