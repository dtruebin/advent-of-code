package icu.trub.aoc.day08

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day08(inputFileName: String) : AbstractDay(inputFileName) {
    private val input = AocUtil.readTxtResource(inputFileName).toList()

    override fun solvePartOne() = with(input) {
        Network.parse(drop(2))
            .navigate("AAA", "ZZZ", first())
    }

    override fun solvePartTwo() = with(input) {
        Network.parse(drop(2))
            .navigate("A", "Z", first())
    }
}