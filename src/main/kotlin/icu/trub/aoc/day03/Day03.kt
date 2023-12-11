package icu.trub.aoc.day03

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.readTxtResource

class Day03(inputFileName: String) : AbstractDay(inputFileName) {
    private val schematic = Schematic.parse(readTxtResource(inputFileName).toList())
    override fun solvePartOne(): Int = schematic.scanForPartNumbers().sum()
    override fun solvePartTwo(): Int = schematic.scanForGearRatios().sum()
}