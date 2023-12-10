package icu.trub.aoc.day07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day07Test {
    @ParameterizedTest
    @CsvSource(
        "day07-test.txt, 6440, 5905",
        "day07-reddit-test.txt, 6592, 6839",
    )
    fun testSolve(inputFileName: String, p1: Int, p2: Int) {
        assertEquals(listOf(p1, p2), Day07(inputFileName).solve())
    }
}