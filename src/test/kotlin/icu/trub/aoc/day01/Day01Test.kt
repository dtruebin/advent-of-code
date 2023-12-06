package icu.trub.aoc.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day01Test {
    @ParameterizedTest
    @MethodSource
    fun testSolve(actual: Int, expected: Int) {
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun testSolve(): Stream<Arguments> = Stream.of(
            arguments(Day01("day01a-test.txt").solveDay1a(), 142),
            arguments(Day01("day01b-test.txt").solveDay1b(), 281),
        )
    }
}