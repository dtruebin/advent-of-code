package icu.trub.aoc.day09

import icu.trub.aoc.AbstractDayTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day09Test : AbstractDayTest<Day09>(Day09::class.java) {
    override val expectedSolutions = listOf(114, 2)

    @ParameterizedTest
    @MethodSource
    fun testExtrapolate(expectedResult: Int, history: List<Int>) {
        assertEquals(expectedResult, history.extrapolate())
    }

    companion object {
        @JvmStatic
        fun testExtrapolate(): Stream<Arguments> = Stream.of(
            arguments(18, listOf(0, 3, 6, 9, 12, 15)),
            arguments(28, listOf(1, 3, 6, 10, 15, 21)),
            arguments(68, listOf(10, 13, 16, 21, 30, 45)),
        )
    }
}