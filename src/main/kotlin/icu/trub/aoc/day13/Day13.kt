package icu.trub.aoc.day13

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.readTxtResource

class Day13(inputFileName: String) : AbstractDay(inputFileName) {
    private val mirrorValley = MirrorValley.parse(this.readTxtResource(inputFileName).toList())

    override fun solvePartOne() = mirrorValley.patterns
        .sumOf { it.getSummary() }
        .toLong()

    override fun solvePartTwo() = mirrorValley.patterns
        .sumOf { it.getSummary(isClean = true) }
        .toLong()
}