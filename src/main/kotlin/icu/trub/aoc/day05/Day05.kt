package icu.trub.aoc.day05

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.readTxtResource

class Day05(inputFileName: String) : AbstractDay(inputFileName) {
    private val almanac = Almanac.parse(readTxtResource(inputFileName).toList())
    override fun solvePartOne(): Int = almanac.locations.min().toInt() // phew, it fits
    override fun solvePartTwo(): Int? = null // TODO("Skipped for now")
}
