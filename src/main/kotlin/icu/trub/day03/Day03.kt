package icu.trub.day03

import icu.trub.AbstractDay
import icu.trub.AocUtil.readTxtResource

class Day03(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solve(): List<Int> = listOf(
        solveDay3a(inputFileName),
        solveDay3b(inputFileName)
    )

    private fun solveDay3a(inputFileName: String): Int = parse(inputFileName).scanForPartNumbers().sum()
    private fun solveDay3b(inputFileName: String): Int = parse(inputFileName).scanForGearRatios().sum()
    private fun parse(inputFileName: String) = Schematic.parse(readTxtResource(inputFileName).toList())
}