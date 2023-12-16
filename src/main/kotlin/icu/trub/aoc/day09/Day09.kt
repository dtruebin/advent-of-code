package icu.trub.aoc.day09

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day09(inputFileName: String) : AbstractDay(inputFileName) {
    private val histories = AocUtil.readTxtResource(inputFileName)
        .map { it.split(" ").map { s -> s.toInt() } }
        .toList()

    override fun solvePartOne() = histories.sumOf { it.extrapolate() }
    override fun solvePartTwo() = histories.sumOf { it.extrapolate(forward = false) }
}

internal fun List<Int>.extrapolate(forward: Boolean = true): Long {
    if (this.all { it == 0 }) return 0
    val extrapolation = zipWithNext { a, b -> b - a }.extrapolate(forward)
    return when {
        forward -> this.last() + extrapolation
        else -> this.first() - extrapolation
    }
}