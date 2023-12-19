package icu.trub.aoc.day13

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day13(inputFileName: String) : AbstractDay(inputFileName) {
    private val mirrorValley = MirrorValley.parse(AocUtil.readTxtResource(inputFileName).toList())

    override fun solvePartOne() = mirrorValley.patterns
        .sumOf { it.getSummary() }
        .toLong()

    override fun solvePartTwo() = mirrorValley.patterns
        .sumOf { it.getSummary(isClean = true) }
        .toLong()
}