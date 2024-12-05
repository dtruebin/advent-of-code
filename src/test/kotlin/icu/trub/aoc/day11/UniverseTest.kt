package icu.trub.aoc.day11

import icu.trub.aoc.AocUtil.readTxtResource
import icu.trub.aoc.util.Point
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UniverseTest {
    private val parsedUniverse = Universe.parse(this.readTxtResource("day11-test.txt"))
    private val expandedUniverse = parsedUniverse.expand()

    @Test
    fun testParse() {
        val universe = parsedUniverse
        assertEquals(9, universe.galaxyIdToCoordinates.size)
        assertEquals(Point(3, 0), universe.get(1))
        assertEquals(Point(9, 6), universe.get(6))
        assertEquals(Point(4, 9), universe.get(9))
    }

    @Test
    fun testExpand() {
        val preExpandedUniverse = Universe.parse(this.readTxtResource("day11-expanded-test.txt"))
        assertEquals(preExpandedUniverse, expandedUniverse)
    }

    @ParameterizedTest
    @MethodSource
    fun testPointToPointDistanceFromMap(idA: Int, idB: Int, expectedDistance: Int) {
        val universe = expandedUniverse
        assertEquals(expectedDistance, universe.get(idA) distanceTo universe.get(idB))
    }

    companion object {
        @JvmStatic
        private fun testPointToPointDistanceFromMap(): Stream<Arguments> = Stream.of(
            // idA, idB, expectedDistance
            arguments(5, 9, 9),
            arguments(1, 7, 15),
            arguments(3, 6, 17),
            arguments(8, 9, 5),
        )
    }
}