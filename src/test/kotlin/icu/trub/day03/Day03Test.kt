package icu.trub.day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Test {
    @Test
    fun testSolve() {
        assertEquals(listOf(4361, 467835), Day03("day03-test.txt").solve())
    }
}