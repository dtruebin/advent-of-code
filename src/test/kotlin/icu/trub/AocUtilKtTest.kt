package icu.trub

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AocUtilKtTest {
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
        "wow, wow",
        "one5eightwo, 1582", // ToDo fails for now
    )
    fun testReplaceWordsWithDigits(input: String, expectedOutput: String) {
        assertEquals(expectedOutput, replaceWordsWithDigits(input))
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
        "one5eightwo, 12", // ToDo fails for now
    )
    fun testDay1bCombo(input: String, expectedOutput: Int) {
        assertEquals(expectedOutput, getDoubleDigitFromFirstAndLast(replaceWordsWithDigits(input)))
    }

}