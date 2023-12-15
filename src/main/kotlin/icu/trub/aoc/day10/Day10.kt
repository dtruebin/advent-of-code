package icu.trub.aoc.day10

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil
import icu.trub.aoc.util.math.getPolygonArea

class Day10(inputFileName: String) : AbstractDay(inputFileName) {
    val input = AocUtil.readTxtResource(inputFileName).toList()
    private val loop = Field.parse(input).findLoop()
    override fun solvePartOne(): Int = loop.size / 2
    override fun solvePartTwo(): Int = loop.let { getPolygonArea(it) - it.size / 2 + 1 }.toInt()
}