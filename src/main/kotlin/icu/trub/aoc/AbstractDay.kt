package icu.trub.aoc

abstract class AbstractDay(val inputFileName: String) {
    /**
     * @return list of solutions, one per day part.
     */
    fun solve(): List<Long?> = listOf(
        solvePartOne(),
        solvePartTwo()
    )

    /**
     * @return the solution for Part One of this day, if available, and `null` otherwise
     */
    protected abstract fun solvePartOne(): Long?

    /**
     * @return the solution for Part Two of this day, if available, and `null` otherwise
     */
    protected abstract fun solvePartTwo(): Long?
}