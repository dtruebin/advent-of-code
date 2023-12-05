package icu.trub.day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun testSolve() {
        assertEquals(4361, solveDay3a("day03-test.txt"))
        assertEquals(467835, solveDay3b("day03-test.txt"))
    }
}