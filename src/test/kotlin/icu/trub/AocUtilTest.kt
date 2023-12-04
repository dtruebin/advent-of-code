package icu.trub

import icu.trub.AocUtil.collectDigitsFromLine
import icu.trub.AocUtil.getDoubleDigitFromFirstAndLast
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AocUtilTest {
    @ParameterizedTest
    @CsvSource(
        "12, 12",
        "123, 13",
        "8, 88",
        "14gxqgqsqqbxfpxnbccjc33eight, 13",
    )
    fun testGetDoubleDigitFromFirstAndLast(input: String, expectedOutput: Int) {
        assertEquals(expectedOutput, getDoubleDigitFromFirstAndLast(input))
    }

    @ParameterizedTest
    @CsvSource(
        "one, 1",
        "onetwo, 12",
        "one5two, 152",
        "'', ''",
        "wow, ''",
        "one5eightwo, 1582",
    )
    fun testCollectDigitsFromLine(input: String, expectedOutput: String) {
        assertEquals(expectedOutput, collectDigitsFromLine(input))
    }

    @ParameterizedTest
    @CsvSource(
        "two1nine, 29",
        "eightwothree, 83",
        "abcone2threexyz, 13",
        "xtwone3four, 24",
        "4nineeightseven2, 42",
        "zoneight234, 14",
        "7pqrstsixteen, 76",
        "one, 11",
        "one5eightwo, 12",
    )
    fun testDay1bCombo(input: String, expectedOutput: Int) {
        assertEquals(expectedOutput, getDoubleDigitFromFirstAndLast(collectDigitsFromLine(input)))
    }

}