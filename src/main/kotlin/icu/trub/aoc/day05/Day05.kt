package icu.trub.aoc.day05

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day05(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solve(): List<Int> = listOf(
        solvePartOne(),
    )

    private val almanac: Almanac
        get() = Almanac.parse(AocUtil.readTxtResource(inputFileName).toList())

    private fun solvePartOne(): Int = almanac.locations.min().toInt() // phew, it fits
}
