package icu.trub.aoc.day12

import kotlin.time.measureTimedValue

internal class SpringRegistry(val records: List<Record>) {
    fun countPossibleArrangements(debug: Boolean = false) = records.map {
        val (arrangements, duration) = measureTimedValue { it.countPossibleArrangements() }
        if (debug && duration.inWholeMilliseconds > 500) println("$duration to process $it")
        arrangements
    }.sumOf { it }

    fun unfold(factor: Int = 5): SpringRegistry = SpringRegistry(records.map { it.unfold(factor) })

    companion object {
        fun parse(input: Sequence<String>): SpringRegistry = input
            .map { Record.parse(it) }
            .toList()
            .let { records -> SpringRegistry(records) }
    }

    internal data class Record(val conditions: String, val damagedGroupSizes: List<Int>) {
        fun countPossibleArrangements(): Long = countPossibleArrangements(conditions, damagedGroupSizes)

        private fun countPossibleArrangements(conditions: String, groups: List<Int>): Long {
            val args = conditions to groups

            with(getCachedArrangementCount(args)) { if (this != null) return this }

            if (conditions.isEmpty()) {
                return when (groups.isEmpty()) {
                    true -> cache(args, 1)
                    else -> cache(args, 0)
                }
            }

            if (conditions.startsWith(WORKING_SPRING)) {
                val remainingConditions = conditions.drop(1)
                return cache(remainingConditions to groups, countPossibleArrangements(remainingConditions, groups))
            } else if (conditions.startsWith(UNKNOWN_SPRING)) {
                val uncoveredWorking = conditions.replaceFirstChar { WORKING_SPRING }
                val uncoveredDamaged = conditions.replaceFirstChar { DAMAGED_SPRING }
                return cache(uncoveredWorking to groups, countPossibleArrangements(uncoveredWorking, groups)) +
                        cache(uncoveredDamaged to groups, countPossibleArrangements(uncoveredDamaged, groups))
            } else if (conditions.startsWith(DAMAGED_SPRING)) {
                val firstGroup = when (groups.isNotEmpty()) {
                    true -> groups.first()
                    else -> return cache(args, 0)
                }

                val canFitFirstGroup = conditions.take(firstGroup).all { it != WORKING_SPRING }
                        && !conditions.drop(firstGroup).startsWith(DAMAGED_SPRING)
                val canFitAllRemainingGroups = groups.sum() + groups.size - 1 <= conditions.length
                if (!canFitFirstGroup || !canFitAllRemainingGroups) {
                    return cache(args, 0)
                }

                val remainingConditions: String = conditions.drop(firstGroup).replaceFirstChar { WORKING_SPRING }
                val remainingGroups: List<Int> = groups.drop(1)
                return cache(
                    remainingConditions to remainingGroups,
                    countPossibleArrangements(remainingConditions, remainingGroups)
                )
            }

            throw IllegalStateException()
        }

        fun unfold(factor: Int): Record = Record(
            List(factor) { conditions }.joinToString("?"),
            buildList { repeat(factor) { addAll(damagedGroupSizes) } }
        )

        companion object {
            const val WORKING_SPRING = '.'
            const val DAMAGED_SPRING = '#'
            const val UNKNOWN_SPRING = '?'

            private val arrangementCountCache = mutableMapOf<Pair<String, List<Int>>, Long>()

            fun cache(key: Pair<String, List<Int>>, arrangementCount: Long): Long =
                arrangementCountCache.computeIfAbsent(key) { arrangementCount }

            fun getCachedArrangementCount(key: Pair<String, List<Int>>): Long? = arrangementCountCache[key]

            fun parse(line: String): Record = line.split(" ")
                .let { (conditions, groupStr) -> conditions to groupStr.split(",").map { it.toInt() } }
                .let { (conditions, groups) -> Record(conditions, groups) }
        }
    }
}