package icu.trub.aoc.day04

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day04(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solve(): List<Int> = listOf(
        solvePartOne(),
    )

    private fun solvePartOne() = AocUtil.readTxtResource(inputFileName)
        .map(Scratchcard.Companion::parse)
        .map(Scratchcard::points)
        .toList()
        .sum()
}