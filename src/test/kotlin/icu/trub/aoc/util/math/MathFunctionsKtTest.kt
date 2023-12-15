package icu.trub.aoc.util.math

import icu.trub.aoc.util.Coordinate
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
            getPolygonArea(listOf(Coordinate(2, 2), Coordinate(4, 10), Coordinate(8, 12), Coordinate(11, 2)))
        )
        assertEquals(
            45.5,
            getPolygonArea(listOf(Coordinate(2, 2), Coordinate(4, 10), Coordinate(9, 7), Coordinate(11, 2)))
        )
    }
}