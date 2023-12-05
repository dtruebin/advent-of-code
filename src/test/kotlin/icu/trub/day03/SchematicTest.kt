package icu.trub.day03

import icu.trub.AocUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SchematicTest {
    private val input: List<String> = AocUtil.readTxtResource("day03-test.txt").toList()
    private val schematic = Schematic.parse(input)

    @Test
    fun testParse() {
        assertEquals('4', schematic.content[Coordinate(0, 0)])
        assertEquals('.', schematic.content[Coordinate(0, input.lastIndex)])
        assertEquals('6', schematic.content[Coordinate(1, input.lastIndex)])
        assertEquals('6', schematic.content[Coordinate(2, input.lastIndex)])
        assertEquals('4', schematic.content[Coordinate(3, input.lastIndex)])
    }

    @ParameterizedTest
    @MethodSource
    fun testGetAdjacentNumbers(x: Int, y: Int, expected: List<Int>) {
        assertEquals(expected, schematic.getAdjacentPartNumbers(Coordinate(x, y)))
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