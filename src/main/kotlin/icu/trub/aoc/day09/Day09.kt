package icu.trub.aoc.day09

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil
import kotlin.streams.asSequence

class Day09(inputFileName: String) : AbstractDay(inputFileName) {
    private val histories = AocUtil.readTxtResource(inputFileName).asSequence()
        .map { it.split(" ").map { s -> s.toInt() } }
        .map { OasisHistory(it) }
        .toList()

    override fun solvePartOne(): Int = histories.sumOf { it.extrapolate() }


    override fun solvePartTwo(): Int? = null // TODO Not yet implemented
}