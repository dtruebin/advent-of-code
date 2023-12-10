package icu.trub.aoc.day07

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil
import kotlin.streams.asSequence

class Day07(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solvePartOne(): Int = with(parseHandToBid(inputFileName)) {
        extractSolution()
    }

    override fun solvePartTwo(): Int = with(parseHandToBid(inputFileName, withJokers = true)) {
        extractSolution()
    }

    private fun Map<Hand, Int>.extractSolution() = keys.sorted()
        .mapIndexed { i, hand -> i.inc() * this[hand]!! }
        .sum()

    private fun parseHandToBid(inputFileName: String, withJokers: Boolean = false) = buildMap {
        AocUtil.readTxtResource(inputFileName).asSequence().forEach { line ->
            line.split(" ").also {
                put(Hand.parse(it[0], withJokers), it[1].toInt())
            }
        }
    }
}