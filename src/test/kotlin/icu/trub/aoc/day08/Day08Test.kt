package icu.trub.aoc.day08

import icu.trub.aoc.AbstractDayTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day08Test : AbstractDayTest<Day08>(Day08::class.java) {
    override val expectedSolutions: List<Int?> = listOf(2, null)

    @Test
    fun testSolveAdditional() {
        assertEquals(listOf(6, null), Day08("day08-additional-test.txt").solve())
    }
}