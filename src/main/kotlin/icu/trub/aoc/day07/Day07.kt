package icu.trub.aoc.day07

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil
import kotlin.streams.asSequence

class Day07(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solvePartOne(): Int = with(parseHandToBid(inputFileName)) {
        keys.sorted()
            .mapIndexed { i, hand -> i.inc() * this[hand]!! }
            .sum()
    }

    override fun solvePartTwo(): Int? = null

    private fun parseHandToBid(inputFileName: String) = buildMap {
        AocUtil.readTxtResource(inputFileName).asSequence().forEach { line ->
            line.split(" ").also {
                put(Hand.parse(it[0]), it[1].toInt())
            }
        }
    }
}