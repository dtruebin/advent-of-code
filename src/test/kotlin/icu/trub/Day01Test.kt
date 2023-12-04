package icu.trub

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
            arguments(solveDay1a("day01a-test.txt"), 142),
            arguments(solveDay1b("day01b-test.txt"), 281),
        )
    }
}