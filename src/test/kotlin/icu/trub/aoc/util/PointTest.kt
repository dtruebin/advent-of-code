package icu.trub.aoc.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PointTest {
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