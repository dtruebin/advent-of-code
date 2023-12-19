package icu.trub.aoc.util.collections

import icu.trub.aoc.util.Point
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CollectionFunctionsKtTest {
    @Test
    fun rotate() {
        val rotated = mapOf(
            Point(0, 0) to 'a', Point(1, 0) to 'b', Point(2, 0) to 'c',
            Point(0, 1) to 'd', Point(1, 1) to 'e', Point(2, 1) to 'f',
        ).rotate()
        val expected = mapOf(
            Point(0, 0) to 'd', Point(1, 0) to 'a',
            Point(0, 1) to 'e', Point(1, 1) to 'b',
            Point(0, 2) to 'f', Point(1, 2) to 'c',
        )
        assertEquals(expected, rotated)
    }
}