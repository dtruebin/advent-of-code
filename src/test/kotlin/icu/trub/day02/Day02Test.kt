package icu.trub.day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun testSolve() {
        assertEquals(8, solveDay2a(bagContent, "day02-test.txt"))
        assertEquals(2286, solveDay2b("day02-test.txt"))
    }
}