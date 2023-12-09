package icu.trub.aoc.day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day06Test {
    private val day06 = Day06("day06-test.txt")

    @Test
    fun testSolve() {
        val solutions = day06.solve()
        assertEquals(listOf(288, 71503), solutions)
    }

    @ParameterizedTest
    @MethodSource
    fun testParse(fixBadKerning: Boolean, expected: List<Race>) {
        val races = day06.parseInput(fixBadKerning)
        assertEquals(expected, races)
    }

    companion object {
        @JvmStatic
        fun testParse(): Stream<Arguments> = Stream.of(

            arguments(
                false, listOf(
                    Race(7, 9),
                    Race(15, 40),
                    Race(30, 200)
                )
            ),

            arguments(
                true, listOf(
                    Race(71530, 940200)
                )
            )

        )
    }
}