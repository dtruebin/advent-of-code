package icu.trub.aoc.day11

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day11(inputFileName: String) : AbstractDay(inputFileName) {
    private val universe = Universe.parse(AocUtil.readTxtResource(inputFileName))
    internal var partTwoExpansionFactor = 1000000
    override fun solvePartOne() = universe.expand()
        .findShortestDistancesBetweenGalaxies()
        .values
        .sumOf { it.toLong() }

    override fun solvePartTwo() = universe.expand(partTwoExpansionFactor)
        .findShortestDistancesBetweenGalaxies()
        .values
        .sumOf { it.toLong() }
}