package icu.trub.aoc.day06

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil.readTxtResource

class Day06(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solvePartOne() = parseInput()
        .extractSolution()

    override fun solvePartTwo() = parseInput(fixBadKerning = true)
        .extractSolution()

    private fun List<Race>.extractSolution() = map { it.getCountOfPossibleWins() }.fold(1L) { a, b -> a * b }

    internal fun parseInput(fixBadKerning: Boolean = false): List<Race> {
        val (times, distances) = this.readTxtResource(inputFileName).toList().map {
            when (fixBadKerning) {
                true -> listOf(splitSingle(it))
                false -> split(it)
            }
        }
        return times.zip(distances).map { Race(it.first, it.second) }
    }

    private fun split(inputLine: String) = inputLine
        .split(":")[1]
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.toLong() }

    private fun splitSingle(inputLine: String) = inputLine
        .split(":")[1]
        .replace("\\D".toRegex(), "")
        .toLong()
}