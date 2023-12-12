package icu.trub.aoc.day09


class OasisHistory(private val numbers: List<Int>) {
    fun extrapolate(): Int = numbers.extrapolate()

    private fun List<Int>.extrapolate(): Int {
        if (this.all { it == 0 }) return 0
        return this.last() + getDiff().extrapolate()
    }

}

internal fun List<Int>.getDiff(): List<Int> = this.zipWithNext { a, b -> b - a }
