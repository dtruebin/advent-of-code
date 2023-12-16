package icu.trub.aoc.day05

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.readTxtResource

class Day05(inputFileName: String) : AbstractDay(inputFileName) {
    private val almanac = Almanac.parse(readTxtResource(inputFileName).toList())
    override fun solvePartOne() = almanac.locations.min()
    override fun solvePartTwo() = null // TODO("Skipped for now")
}
