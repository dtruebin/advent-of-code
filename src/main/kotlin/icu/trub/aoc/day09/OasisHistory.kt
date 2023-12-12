package icu.trub.aoc.day09


class OasisHistory(private val numbers: List<Int>) {
    fun extrapolate(forward: Boolean = true): Int = numbers.extrapolate(forward)

    private fun List<Int>.extrapolate(forward: Boolean = true): Int {
        if (this.all { it == 0 }) return 0
        val extrapolation = getDiff().extrapolate(forward)
        return when {
            forward -> this.last() + extrapolation
            else -> this.first() - extrapolation
        }
    }

}

internal fun List<Int>.getDiff(): List<Int> = this.zipWithNext { a, b -> b - a }
