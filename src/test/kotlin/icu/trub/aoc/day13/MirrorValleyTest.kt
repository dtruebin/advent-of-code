package icu.trub.aoc.day13

import icu.trub.aoc.AocUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MirrorValleyTest {
    @Test
    fun testParse() {
        assertEquals(2, parsed.patterns.size)
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    inner class PatternTest {
        @Test
        fun testParse() {
            assertAll(
                // both test patterns are 9x7
                { assertTrue(parsed.patterns.all { it.matrix.maxOf { entry -> entry.key.x } == 8 }) },
                { assertTrue(parsed.patterns.all { it.matrix.maxOf { entry -> entry.key.y } == 6 }) },
            )
        }

        @Test
        fun testWidthHeight() {
            assertAll(
                // both test patterns are 9x7
                { assertTrue(parsed.patterns.all { it.lastColIndex == 8 }) },
                { assertTrue(parsed.patterns.all { it.lastRowIndex == 6 }) },
            )
        }

        @Test
        fun testGetX() {
            val pattern = parsed.patterns[0]
            assertAll(
                { assertEquals("#.##..##.", pattern.getHorizontalLine(0)) },
                { assertEquals("#.#.##.#.", pattern.getHorizontalLine(pattern.lastRowIndex)) },
                { assertEquals("", pattern.getHorizontalLine(pattern.lastRowIndex + 1)) },
            )
        }

        @Test
        fun testGetY() {
            val pattern = parsed.patterns[0]
            assertAll(
                { assertEquals("#.##..#", pattern.getVerticalLine(0)) },
                { assertEquals("..##...", pattern.getVerticalLine(pattern.lastColIndex)) },
                { assertEquals("", pattern.getVerticalLine(pattern.lastColIndex + 1)) },
            )
        }

        @ParameterizedTest
        @MethodSource("reflectionArgs")
        internal fun testReflection(pattern: Pattern, expectedReflection: Pattern.MirrorReflection) {
            assertEquals(expectedReflection, pattern.reflection)
        }

        private fun reflectionArgs(): Stream<Arguments> = Stream.of(
            arguments(parsed.patterns[0], Pattern.LeftRightReflection(5)),
            arguments(parsed.patterns[1], Pattern.UpDownReflection(4)),
            arguments(patternWithSubSymmetry, Pattern.LeftRightReflection(8)),
            arguments(patternWithTwoSymLines, Pattern.LeftRightReflection(3)),
        )

        @ParameterizedTest
        @MethodSource("cleanReflectionArgs")
        internal fun testCleanReflection(pattern: Pattern, expectedReflection: Pattern.MirrorReflection) {
            assertEquals(expectedReflection, pattern.altReflection)
        }

        private fun cleanReflectionArgs(): Stream<Arguments> = Stream.of(
            arguments(parsed.patterns[0], Pattern.UpDownReflection(3)),
            arguments(parsed.patterns[1], Pattern.UpDownReflection(1)),
        )

        @ParameterizedTest
        @MethodSource("summaryArgs")
        internal fun testSummary(pattern: Pattern, expectedSummary: Int, expectedSummaryClean: Int) {
            assertAll(
                { assertEquals(expectedSummary, pattern.getSummary()) },
                { assertEquals(expectedSummaryClean, pattern.getSummary(isClean = true)) },
            )
        }

        private fun summaryArgs(): Stream<Arguments> = Stream.of(
            arguments(parsed.patterns[0], 5, 300),
            arguments(parsed.patterns[1], 400, 100),
        )
    }

    companion object {
        private val parsed = MirrorValley.parse(AocUtil.readTxtResource("day13-test.txt").toList())

        private val parsedAdditional = MirrorValley.parse(AocUtil.readTxtResource("day13-additional-test.txt").toList())
        private val patternWithSubSymmetry = parsedAdditional.patterns[0]
        private val patternWithTwoSymLines = parsedAdditional.patterns[1]
    }
}