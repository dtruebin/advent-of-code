package icu.trub.aoc.day10

import icu.trub.aoc.AbstractDayTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day10Test : AbstractDayTest<Day10>(Day10::class.java) {
    override val expectedSolutions = listOf(8, 1)

    @ParameterizedTest
    @MethodSource
    fun testSolvePartTwo(inputFileName: String, expectedOutput: Long) {
        assertEquals(expectedOutput, Day10(inputFileName).solve()[1])
    }

    companion object {
        @JvmStatic
        fun testSolvePartTwo(): Stream<Arguments> = Stream.of(
            arguments("day10b-test.txt", 10),
            arguments("day10c-test.txt", 8),
        )
    }
}