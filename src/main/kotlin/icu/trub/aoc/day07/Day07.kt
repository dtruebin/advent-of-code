package icu.trub.aoc.day07

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day07(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solvePartOne() = parseHandToBid(inputFileName)
        .extractSolution()

    override fun solvePartTwo() = parseHandToBid(inputFileName, withJokers = true)
        .extractSolution()

    private fun Map<Hand, Int>.extractSolution() = keys.sorted()
        .mapIndexed { i, hand -> i.inc() * this[hand]!! }
        .sum()
        .toLong()

    private fun parseHandToBid(inputFileName: String, withJokers: Boolean = false) = buildMap {
        AocUtil.readTxtResource(inputFileName).forEach { line ->
            line.split(" ").also {
                put(Hand.parse(it[0], withJokers), it[1].toInt())
            }
        }
    }
}