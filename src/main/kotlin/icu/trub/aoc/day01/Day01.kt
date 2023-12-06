package icu.trub.aoc.day01

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.collectDigitsFromLine
import icu.trub.aoc.AocUtil.getDoubleDigitFromFirstAndLast
import icu.trub.aoc.AocUtil.readTxtResource

class Day01(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solve(): List<Int> = listOf(
        solveDay1a(),
        solveDay1b()
    )

    fun solveDay1a(): Int =
        readTxtResource(inputFileName)
            .map(::getDoubleDigitFromFirstAndLast)
            .toList()
            .sum()

    fun solveDay1b(): Int =
        readTxtResource(inputFileName)
            .map(::collectDigitsFromLine)
            .map(::getDoubleDigitFromFirstAndLast)
            .toList()
            .sum()
}