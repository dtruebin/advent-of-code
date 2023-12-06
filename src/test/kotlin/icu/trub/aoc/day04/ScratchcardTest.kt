package icu.trub.aoc.day04

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ScratchcardTest {
    @ParameterizedTest
    @MethodSource
    fun testPoints(scratchcard: Scratchcard, expected: Int) {
        assertEquals(expected, scratchcard.points)
    }

    @Test
    fun testParse() {
        assertEquals(testScratchcard, Scratchcard.parse("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"))
    }

    companion object {
        val testScratchcard = Scratchcard(setOf(41, 48, 83, 86, 17), setOf(83, 86, 6, 31, 17, 9, 48, 53))

        @JvmStatic
        fun testPoints(): Stream<Arguments> {
            return Stream.of(
                arguments(testScratchcard, 8),
                arguments(Scratchcard(setOf(), setOf(1, 2, 3)), 0),
            )
        }
    }
}