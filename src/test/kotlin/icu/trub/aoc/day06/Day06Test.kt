package icu.trub.aoc.day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06Test {
    private val day06 = Day06("day06-test.txt")

    @Test
    fun testSolve() {
        val solutions = day06.solve()
        assertEquals(listOf(288), solutions)
    }

    @Test
    fun testParse() {
        val races = day06.parseInput()
        val expected = listOf(
            Race(7, 9),
            Race(15, 40),
            Race(30, 200)
        )
        assertEquals(expected, races)
    }
}