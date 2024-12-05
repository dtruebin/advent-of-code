package icu.trub.aoc.day03

import icu.trub.aoc.AocUtil.readTxtResource
import icu.trub.aoc.util.Point
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SchematicTest {
    private val input: List<String> = this.readTxtResource("day03-test.txt").toList()
    private val schematic = Schematic.parse(input)

    @Test
    fun testParse() {
        assertEquals('4', schematic.content[Point(0, 0)])
        assertEquals('.', schematic.content[Point(0, input.lastIndex)])
        assertEquals('6', schematic.content[Point(1, input.lastIndex)])
        assertEquals('6', schematic.content[Point(2, input.lastIndex)])
        assertEquals('4', schematic.content[Point(3, input.lastIndex)])
    }

    @ParameterizedTest
    @MethodSource
    fun testGetAdjacentNumbers(x: Int, y: Int, expected: List<Int>) {
        assertEquals(expected, schematic.getAdjacentPartNumbers(Point(x, y)))
    }

    companion object {
        @JvmStatic
        fun testGetAdjacentNumbers(): Stream<Arguments> = Stream.of(
            Arguments.arguments(6, 3, listOf(633)),
            Arguments.arguments(3, 1, listOf(467, 35)),
            Arguments.arguments(3, 4, listOf(617)),
        )
    }
}