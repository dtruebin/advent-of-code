package icu.trub.aoc.day03

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.readTxtResource

class Day03(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solvePartOne(): Int = parse(inputFileName).scanForPartNumbers().sum()
    override fun solvePartTwo(): Int = parse(inputFileName).scanForGearRatios().sum()
    private fun parse(inputFileName: String) = Schematic.parse(readTxtResource(inputFileName).toList())
}