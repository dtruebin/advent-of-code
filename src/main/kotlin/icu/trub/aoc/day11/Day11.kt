package icu.trub.aoc.day11

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day11(inputFileName: String) : AbstractDay(inputFileName) {
    private val universe = Universe.parse(AocUtil.readTxtResource(inputFileName))
    override fun solvePartOne() = universe.expand()
        .findShortestDistancesBetweenGalaxies()
        .values
        .sum()

    override fun solvePartTwo() = null
}