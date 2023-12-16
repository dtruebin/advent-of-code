package icu.trub.aoc.day01

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.collectDigitsFromLine
import icu.trub.aoc.AocUtil.getDoubleDigitFromFirstAndLast
import icu.trub.aoc.AocUtil.readTxtResource

class Day01(inputFileName: String) : AbstractDay(inputFileName) {
    public override fun solvePartOne() = readTxtResource(inputFileName)
        .sumOf { getDoubleDigitFromFirstAndLast(it).toLong() }

    public override fun solvePartTwo() = readTxtResource(inputFileName)
        .map(::collectDigitsFromLine)
        .sumOf { getDoubleDigitFromFirstAndLast(it).toLong() }
}