package icu.trub.aoc

import icu.trub.aoc.AocUtil.collectDigitsFromLine
import icu.trub.aoc.AocUtil.createAllDays
import icu.trub.aoc.AocUtil.getDoubleDigitFromFirstAndLast
import icu.trub.aoc.AocUtil.zipWithReversed
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
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

    @Test
    fun testCreateAllDays() {
        val resourceName = "${this.javaClass.packageName.replace('.', '/')}/day01/day01.txt"
        val inputFile = this.javaClass.classLoader.getResource(resourceName)
        assumeTrue(inputFile != null, "Assumed resource $resourceName to be present, but it is missing.")
        assertTrue(createAllDays().count() > 0)
    }

    @Test
    fun testRangeZipWithReversed() {
        val zipped = (0..3).zipWithReversed()
        val expected = listOf(0 to 3, 1 to 2, 2 to 1, 3 to 0)
        assertEquals(expected, zipped)
    }
}