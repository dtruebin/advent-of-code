package icu.trub.aoc.day06

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RaceTest {
    private val race = Race(7, 9)

    @ParameterizedTest
    @CsvSource(
        value = [
            "0,0",
            "4,12",
            "6,6",
            "7,0"
        ]
    )
    fun testGetOutcome(buttonHoldTime: Int, expectedDistance: Int) {
        assertEquals(expectedDistance, race.getOutcome(buttonHoldTime))
    }

    @Test
    fun testGetCountOfPossibleWins() {
        assertEquals(4, race.getCountOfPossibleWins())
    }
}