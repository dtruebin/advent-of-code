package icu.trub.aoc.day04

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors.toMap

class Day04(inputFileName: String) : AbstractDay(inputFileName) {
    override fun solvePartOne() = AocUtil.readTxtResource(inputFileName)
        .map(Scratchcard.Companion::parse)
        .map(Scratchcard::points)
        .toList()
        .sum()

    override fun solvePartTwo(): Int {
        val originalCards = AocUtil.readTxtResource(inputFileName)
            .map(Scratchcard.Companion::parse)
            .collect(toMap(Scratchcard::id, Function.identity()))

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