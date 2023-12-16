package icu.trub.aoc.day03

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.readTxtResource

class Day03(inputFileName: String) : AbstractDay(inputFileName) {
    private val schematic = Schematic.parse(readTxtResource(inputFileName).toList())
    override fun solvePartOne() = schematic.scanForPartNumbers().sum().toLong()
    override fun solvePartTwo() = schematic.scanForGearRatios().sum().toLong()
}