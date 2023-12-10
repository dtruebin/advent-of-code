package icu.trub.aoc

abstract class AbstractDay(val inputFileName: String) {
    /**
     * @return list of solutions, one per day part.
     */
    fun solve(): List<Int> = listOf(
        solvePartOne(),
        solvePartTwo()
    )

    /**
     * @return the solution for Part One of this day
     */
    protected abstract fun solvePartOne(): Int

    /**
     * @return the solution for Part Two of this day
     */
    protected abstract fun solvePartTwo(): Int
}