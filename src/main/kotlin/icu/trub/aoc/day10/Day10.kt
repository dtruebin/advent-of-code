package icu.trub.aoc.day10

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil
import icu.trub.aoc.util.math.getPolygonArea

class Day10(inputFileName: String) : AbstractDay(inputFileName) {
    val input = AocUtil.readTxtResource(inputFileName).toList()
    private val loop = Field.parse(input).findLoop()
    override fun solvePartOne() = loop.size / 2L
    override fun solvePartTwo() = loop.let { getPolygonArea(it) - it.size / 2 + 1 }.toLong()
}