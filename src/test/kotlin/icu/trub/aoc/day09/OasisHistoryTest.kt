package icu.trub.aoc.day09

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class OasisHistoryTest {
    @ParameterizedTest
    @MethodSource
    fun testExtrapolate(expectedResult: Int, history: OasisHistory) {
        assertEquals(expectedResult, history.extrapolate())
    }

    @ParameterizedTest
    @MethodSource
    fun testGetDiff(input: List<Int>, expectedOutput: List<Int>) {
        assertEquals(expectedOutput, input.getDiff())
    }

    companion object {
        @JvmStatic
        fun testExtrapolate(): Stream<Arguments> = Stream.of(
            arguments(18, OasisHistory(listOf(0, 3, 6, 9, 12, 15))),
            arguments(28, OasisHistory(listOf(1, 3, 6, 10, 15, 21))),
            arguments(68, OasisHistory(listOf(10, 13, 16, 21, 30, 45))),
        )

        @JvmStatic
        fun testGetDiff(): Stream<Arguments> = Stream.of(
            arguments(listOf(0, 3, 6, 9, 12, 15), List(5) { 3 }),
            arguments(listOf(3, 3, 3), listOf(0, 0)),
            arguments(listOf(3, 3), listOf(0)),
            arguments(listOf(0, 0), listOf(0)),
            arguments(listOf(1, 3, 6, 10, 15, 21), listOf(2, 3, 4, 5, 6)),
        )
    }
}