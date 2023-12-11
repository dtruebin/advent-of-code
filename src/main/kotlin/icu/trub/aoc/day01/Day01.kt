package icu.trub.aoc.day01

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.collectDigitsFromLine
import icu.trub.aoc.AocUtil.getDoubleDigitFromFirstAndLast
import icu.trub.aoc.AocUtil.readTxtResource
import kotlin.streams.asSequence

class Day01(inputFileName: String) : AbstractDay(inputFileName) {
    public override fun solvePartOne(): Int = readTxtResource(inputFileName).asSequence()
        .sumOf { getDoubleDigitFromFirstAndLast(it) }

    public override fun solvePartTwo(): Int = readTxtResource(inputFileName).asSequence()
        .map(::collectDigitsFromLine)
        .sumOf { getDoubleDigitFromFirstAndLast(it) }
}