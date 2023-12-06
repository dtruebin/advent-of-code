package icu.trub.aoc.day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun testSolve() {
        assertEquals(listOf(8, 2286), Day02("day02-test.txt").solve())
    }
}