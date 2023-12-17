package icu.trub.aoc.day12

import kotlin.time.measureTimedValue

internal class SpringRegistry(val records: List<Record>) {
    fun countPossibleArrangements(debug: Boolean = false) = records.map {
        val (arrangements, duration) = measureTimedValue { it.countPossibleArrangements() }
        if (debug && duration.inWholeMilliseconds > 500) println("$duration to process $it")
        arrangements
    }.sumOf { it }.toLong()

    companion object {
        fun parse(input: Sequence<String>): SpringRegistry = input
            .map { Record.parse(it) }
            .toList()
            .let { records -> SpringRegistry(records) }
    }

    internal data class Record(val conditions: String, val damagedGroupSizes: List<Int>) {
        fun countPossibleArrangements(): Int = countPossibleArrangements(conditions, damagedGroupSizes)

        private fun countPossibleArrangements(conditions: String, groups: List<Int>): Int {
            if (conditions.isEmpty()) {
                return if (groups.isEmpty()) 1 else 0
            }

            if (conditions.startsWith(WORKING_SPRING)) {
                return countPossibleArrangements(conditions.drop(1), groups)
            } else if (conditions.startsWith(UNKNOWN_SPRING)) {
                return countPossibleArrangements(
                    conditions.replaceFirstChar { WORKING_SPRING }, groups
                ) + countPossibleArrangements(
                    conditions.replaceFirstChar { DAMAGED_SPRING }, groups
                )
            } else if (conditions.startsWith(DAMAGED_SPRING)) {
                val firstGroup = when (groups.isNotEmpty()) {
                    true -> groups.first()
                    else -> return 0
                }

                val canFitFirstGroup = conditions.take(firstGroup).all { it != WORKING_SPRING }
                        && !conditions.drop(firstGroup).startsWith(DAMAGED_SPRING)
                val canFitAllRemainingGroups = groups.sum() + groups.size - 1 <= conditions.length
                if (!canFitFirstGroup || !canFitAllRemainingGroups) {
                    return 0
                }

                val remainingConditions: String = conditions.drop(firstGroup).replaceFirstChar { WORKING_SPRING }
                val remainingGroups: List<Int> = groups.drop(1)
                return countPossibleArrangements(remainingConditions, remainingGroups)
            }

            throw IllegalStateException()
        }

        companion object {
            const val WORKING_SPRING = '.'
            const val DAMAGED_SPRING = '#'
            const val UNKNOWN_SPRING = '?'

            fun parse(line: String): Record = line.split(" ")
                .let { (conditions, groupStr) -> conditions to groupStr.split(",").map { it.toInt() } }
                .let { (conditions, groups) -> Record(conditions, groups) }
        }
    }
}