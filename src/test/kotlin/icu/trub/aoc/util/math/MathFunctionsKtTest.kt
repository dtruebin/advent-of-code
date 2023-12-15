package icu.trub.aoc.util.math

import icu.trub.aoc.util.Point
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MathFunctionsKtTest {
    /**
     * https://www.mathopenref.com/coordpolygonarea.html
     */
    @Test
    fun testGetPolygonArea() {
        assertEquals(
            59.0,
            getPolygonArea(listOf(Point(2, 2), Point(4, 10), Point(8, 12), Point(11, 2)))
        )
        assertEquals(
            45.5,
            getPolygonArea(listOf(Point(2, 2), Point(4, 10), Point(9, 7), Point(11, 2)))
        )
    }
}