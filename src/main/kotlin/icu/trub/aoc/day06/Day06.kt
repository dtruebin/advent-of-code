package icu.trub.aoc.day06

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day06(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solve(): List<Int> = listOf(
        solvePartOne(),
        solvePartTwo()
    )

    private fun solvePartOne(): Int {
        val races = parseInput()
        return races.map { it.getCountOfPossibleWins() }.fold(1) { a, b -> a * b }
    }

    private fun solvePartTwo(): Int {
        val races = parseInput(fixBadKerning = true)
        return races.map { it.getCountOfPossibleWins() }.fold(1) { a, b -> a * b }
    }

    internal fun parseInput(fixBadKerning: Boolean = false): List<Race> {
        val (times, distances) = AocUtil.readTxtResource(inputFileName).toList().map {
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