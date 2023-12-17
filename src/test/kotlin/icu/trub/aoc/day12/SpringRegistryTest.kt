package icu.trub.aoc.day12

import icu.trub.aoc.AocUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.time.measureTimedValue

class SpringRegistryTest {
    companion object {
        private val parsedRegistry = SpringRegistry.parse(AocUtil.readTxtResource("day12-test.txt"))
        private val parsedFirstRecord = SpringRegistry.Record.parse("???.### 1,1,3")
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Record {
        @Test
        fun testParse() {
            assertEquals("???.###", parsedFirstRecord.conditions)
            assertEquals(listOf(1, 1, 3), parsedFirstRecord.damagedGroupSizes)
        }

        @Test
        fun testRegex() {
            assertEquals("[^#]*#{1}[^#]+#{1}[^#]+#{3}[^#]*", parsedFirstRecord.damagedGroupRegex.toString())
        }

        @ParameterizedTest
        @MethodSource("testGetPossibleArrangementsArgs")
        internal fun testGetPossibleArrangements(record: SpringRegistry.Record, expectedCount: Int) {
            val (arrangements, duration) = measureTimedValue { record.countPossibleArrangements() }
            assertAll(
                { assertEquals(expectedCount, arrangements) },
                { assertTrue(duration.inWholeSeconds < 1, "ran too long") },
            )
        }

        private fun testGetPossibleArrangementsArgs() = parsedRegistry.records
            .zip(listOf(1, 4, 1, 1, 4, 10))
            .map { arguments(it.first, it.second) }
            .plus(arguments(SpringRegistry.Record.parse("??????.????#???#???# 4,2,1,1,3,1"), 6))
            .plus(arguments(SpringRegistry.Record.parse("??????#????????##??? 1,1,1,1,1,9"), 3))
    }

    @Nested
    inner class Registry {
        @Test
        fun testParse() {
            assertEquals(6, parsedRegistry.records.size)
            assertEquals(parsedFirstRecord, parsedRegistry.records.first())
        }

        @Test
        fun testCountPossibleArrangements_sameRecords_noConflation() {
            val record = parsedFirstRecord
            val recordArrangementsCount = record.countPossibleArrangements()
            val registry = SpringRegistry(listOf(record, record))
            assertEquals(recordArrangementsCount * 2L, registry.countPossibleArrangements())
        }
    }
}