package icu.trub.aoc.day07

import icu.trub.aoc.AbstractDayTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day07Test : AbstractDayTest<Day07>(Day07::class.java) {
    override val expectedSolutions = listOf(6440, 5905)

    @Test
    fun testSolveReddit() {
        assertEquals(listOf<Long>(6592, 6839), Day07("day07-reddit-test.txt").solve())
    }
}