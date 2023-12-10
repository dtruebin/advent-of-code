package icu.trub.aoc.day01

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.collectDigitsFromLine
import icu.trub.aoc.AocUtil.getDoubleDigitFromFirstAndLast
import icu.trub.aoc.AocUtil.readTxtResource

class Day01(inputFileName: String) : AbstractDay(inputFileName) {
    public override fun solvePartOne(): Int =
        readTxtResource(inputFileName)
            .map(::getDoubleDigitFromFirstAndLast)
            .toList()
            .sum()

    public override fun solvePartTwo(): Int =
        readTxtResource(inputFileName)
            .map(::collectDigitsFromLine)
            .map(::getDoubleDigitFromFirstAndLast)
            .toList()
            .sum()
}