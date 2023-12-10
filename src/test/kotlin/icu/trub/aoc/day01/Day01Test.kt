package icu.trub.aoc.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun testSolvePartOne() {
        assertEquals(142, Day01("day01a-test.txt").solvePartOne())
    }

    @Test
    fun testSolvePartTwo() {
        assertEquals(281, Day01("day01b-test.txt").solvePartTwo())
    }
}