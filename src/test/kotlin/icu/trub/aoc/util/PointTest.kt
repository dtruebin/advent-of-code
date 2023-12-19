package icu.trub.aoc.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PointTest {
    @Test
    fun testRowToString() {
        val colIndex = 1
        val rowIndex = 22
        val pointCharMap = mapOf(
            Point(colIndex, rowIndex)
                    to '1',
            Point(colIndex + 1, 33)
                    to '2',
            Point(colIndex + 2, rowIndex)
                    to '3',
            Point(colIndex - 100, rowIndex)
                    to '4',
        )
        assertEquals("413", pointCharMap.rowToString(rowIndex))
    }

    @ParameterizedTest
    @MethodSource
    fun testPointToPointDistance(a: Point, b: Point, expectedDistance: Int) {
        assertEquals(expectedDistance, a distanceTo b)
    }

    companion object {
        @JvmStatic
        private fun testPointToPointDistance(): Stream<Arguments> = Stream.of(
            Arguments.arguments(Point(1, 6), Point(5, 11), 9),
            Arguments.arguments(Point(5, 11), Point(1, 6), 9), // i.e., order shouldn't matter
        )
    }
}