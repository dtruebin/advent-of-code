package icu.trub

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class AocUtilKtTest {
    @ParameterizedTest
    @MethodSource
    fun testGetDoubleDigitFromFirstAndLast(input: String, expectedOutput: Int) {
        assertEquals(expectedOutput, getDoubleDigitFromFirstAndLast(input))
    }

    @ParameterizedTest
    @MethodSource
    fun testReplaceWordsWithDigits(input: String, expectedOutput: String) {
        assertEquals(expectedOutput, replaceWordsWithDigits(input))
    }

    @ParameterizedTest
    @MethodSource
    fun testDay2Combo(input: String, expectedOutput: Int) {
        assertEquals(expectedOutput, getDoubleDigitFromFirstAndLast(replaceWordsWithDigits(input)))
    }

    companion object {
        @JvmStatic
        fun testGetDoubleDigitFromFirstAndLast(): Stream<Arguments> = Stream.of(
            arguments("12", 12),
            arguments("123", 13),
            arguments("8", 88),
            arguments("14gxqgqsqqbxfpxnbccjc33eight", 13),
        )

        @JvmStatic
        fun testReplaceWordsWithDigits(): Stream<Arguments> = Stream.of(
            arguments("one", "1"),
            arguments("onetwo", "12"),
            arguments("one5two", "152"),
        )

        @JvmStatic
        fun testDay2Combo(): Stream<Arguments> = Stream.of(
            arguments("two1nine", 29),
            arguments("eightwothree", 83),
            arguments("abcone2threexyz", 13),
            arguments("xtwone3four", 24),
            arguments("4nineeightseven2", 42),
            arguments("zoneight234", 14),
            arguments("7pqrstsixteen", 76),
            arguments("one", 11),
        )
    }
}