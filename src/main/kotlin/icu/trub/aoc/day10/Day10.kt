package icu.trub.aoc.day10

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day10(inputFileName: String) : AbstractDay(inputFileName) {
    val input = AocUtil.readTxtResource(inputFileName).toList()
    override fun solvePartOne(): Int = Field.parse(input).findLoop().size / 2
    override fun solvePartTwo(): Int? = null
}