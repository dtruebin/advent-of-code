package icu.trub.day01

import icu.trub.AbstractDay
import icu.trub.AocUtil.collectDigitsFromLine
import icu.trub.AocUtil.getDoubleDigitFromFirstAndLast
import icu.trub.AocUtil.readTxtResource

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