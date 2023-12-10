package icu.trub.aoc.day05

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day05(inputFileName: String) : AbstractDay(inputFileName) {
    private val almanac: Almanac
        get() = Almanac.parse(AocUtil.readTxtResource(inputFileName).toList())

    override fun solvePartOne(): Int = almanac.locations.min().toInt() // phew, it fits

    override fun solvePartTwo(): Int {
        TODO("Skipped for now")
    }
}
