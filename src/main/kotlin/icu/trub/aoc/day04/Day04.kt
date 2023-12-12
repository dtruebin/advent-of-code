package icu.trub.aoc.day04

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil
import java.util.*

class Day04(inputFileName: String) : AbstractDay(inputFileName) {
    private val input
        get() = AocUtil.readTxtResource(inputFileName).map { Scratchcard.parse(it) }

    override fun solvePartOne() = input.sumOf { it.points }

    override fun solvePartTwo(): Int {
        val originalCards = input.associateBy { it.id }

        val cardQueue = PriorityQueue(Comparator.comparingInt(Scratchcard::id))
        cardQueue.addAll(originalCards.values)

        val resultingCards = mutableListOf<Scratchcard>()
        while (true) {
            with(cardQueue.poll() ?: break) {
                resultingCards.add(this)
                for (i in id + 1..<id + 1 + matchingNumbers.size) {
                    cardQueue.add(originalCards[i]!!.copy())
                }
            }
        }
        return resultingCards.size
    }
}