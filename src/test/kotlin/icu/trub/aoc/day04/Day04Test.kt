package icu.trub.aoc.day04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day04Test {
    @Test
    fun solve() {
        assertEquals(listOf(13, 30), Day04("day04-test.txt").solve())
    }
}