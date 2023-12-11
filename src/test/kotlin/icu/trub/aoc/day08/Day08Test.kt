package icu.trub.aoc.day08

import icu.trub.aoc.AbstractDayTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class Day08Test : AbstractDayTest<Day08>(Day08::class.java) {
    override val expectedSolutions: List<Int?> = listOf(2, 2)

    @Test
    fun testSolveAdditional() {
        assertAll(
            { assertEquals(6, Day08("day08a-additional-test.txt").solve()[0]) },
            { assertEquals(6, Day08("day08b-additional-test.txt").solve()[1]) },
        )
    }
}