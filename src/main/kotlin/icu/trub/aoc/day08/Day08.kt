package icu.trub.aoc.day08

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil
import kotlin.streams.asSequence

class Day08(inputFileName: String) : AbstractDay(inputFileName) {
    private val input = AocUtil.readTxtResource(inputFileName).asSequence().toList()

    override fun solvePartOne(): Int = with(input) {
        Network.parse(drop(2))
            .navigate("AAA", "ZZZ", first())
            .toInt()
    }

    override fun solvePartTwo(): Int = with(input) {
        Network.parse(drop(2))
            .navigate("A", "Z", first())
            .also { if (it > Int.MAX_VALUE) println("Day 8, Part Two - actual solution (before conversion to Int): $it") }
            .toInt()
    }
}