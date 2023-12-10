package icu.trub.aoc.day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day05Test {
    @Test
    fun solve() {
        val solutions = Day05("day05-test.txt").solve()
        assertEquals(listOf(35, null), solutions)
    }
}