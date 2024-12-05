package icu.trub.aoc.day12

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.readTxtResource

class Day12(inputFileName: String) : AbstractDay(inputFileName) {
    private val springRegistry = SpringRegistry.parse(this.readTxtResource(inputFileName))
    override fun solvePartOne() = springRegistry.countPossibleArrangements()
    override fun solvePartTwo() = springRegistry.unfold().countPossibleArrangements()
}