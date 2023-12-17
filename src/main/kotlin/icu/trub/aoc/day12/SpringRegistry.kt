package icu.trub.aoc.day12

import icu.trub.aoc.util.math.subsets
import icu.trub.aoc.util.string.replaceCharAtIndices
import kotlin.time.measureTimedValue

internal class SpringRegistry(val records: List<Record>) {
    fun countPossibleArrangements(debug: Boolean = false) = records.map {
        val (arrangements, duration) = measureTimedValue { it.getPossibleArrangements() }
        if (debug && duration.inWholeMilliseconds > 500) println("$duration to process $it")
        arrangements
    }.sumOf { it.size }.toLong()

    companion object {
        fun parse(input: Sequence<String>): SpringRegistry = input
            .map { Record.parse(it) }
            .toList()
            .let { records -> SpringRegistry(records) }
    }

    internal data class Record(val conditions: String, val damagedGroupSizes: List<Int>) {
        val damagedGroupRegex: Regex = Regex(buildString {
            val nonDamagedZeroOrMore = "[^${DAMAGED_SPRING}]*"
            append(nonDamagedZeroOrMore)
            for ((index, groupSize) in damagedGroupSizes.withIndex()) {
                append("#{$groupSize}")
                if (index != damagedGroupSizes.lastIndex) append("[^${DAMAGED_SPRING}]+")
            }
            append(nonDamagedZeroOrMore)
        })

        fun getPossibleArrangements(): List<String> {
            if (conditions.length == damagedGroupSizes.sum() + damagedGroupSizes.size - 1) {
                return listOf(damagedGroupSizes.joinToString(".") { DAMAGED_SPRING.toString().repeat(it) })
            }

            val (optimizedGroups, optimizedConditions) = simplifyRecordData(damagedGroupSizes, conditions)
            if (optimizedGroups != this.damagedGroupSizes) {
                // TODO not cool to return arrangements for technically some other record. Switch to returning count?
                return Record(optimizedConditions, optimizedGroups).getPossibleArrangements()
            }

            return this.conditions.withIndex()
                .filter { it.value == UNKNOWN_SPRING }
                .map { it.index }
                .toSet()
                .subsets()
                .map { this.conditions.replaceCharAtIndices(it, DAMAGED_SPRING) }
                .filter { damagedGroupRegex.matches(it) }
                .toList()
        }

        /**
         * If possible, trims [initialGroups] and [initialConditions] by removing insignificant groups at both ends.
         */
        private fun simplifyRecordData(initialGroups: List<Int>, initialConditions: String): Pair<List<Int>, String> {
            var optimizedGroups = initialGroups
            var optimizedConditions = initialConditions
            while (optimizedConditions.endsWith(DAMAGED_SPRING)) {
                val lastGroupSize = optimizedGroups.last()
                optimizedGroups = optimizedGroups.dropLast(1)
                optimizedConditions = optimizedConditions.dropLast(lastGroupSize + 1)
            }
            while (optimizedConditions.startsWith(DAMAGED_SPRING)) {
                val firstGroupSize = optimizedGroups.first()
                optimizedGroups = optimizedGroups.drop(1)
                optimizedConditions = optimizedConditions.drop(firstGroupSize + 1)
            }
            return Pair(optimizedGroups, optimizedConditions)
        }

        companion object {
            const val DAMAGED_SPRING = '#'
            const val UNKNOWN_SPRING = '?'

            fun parse(line: String): Record = line.split(" ")
                .let { (conditions, groupStr) -> conditions to groupStr.split(",").map { it.toInt() } }
                .let { (conditions, groups) -> Record(conditions, groups) }
        }
    }
}