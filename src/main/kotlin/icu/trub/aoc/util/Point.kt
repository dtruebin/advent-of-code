package icu.trub.aoc.util

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    fun left() = Point(x - 1, y)
    fun right() = Point(x + 1, y)
    fun up() = Point(x, y - 1)
    fun down() = Point(x, y + 1)
    infix fun distanceTo(other: Point): Int = abs(x - other.x) + abs(y - other.y)
}

/** Returns all symbols at row index [y], ordered by `x`, as a single string. */
fun Map<Point, Char>.rowToString(y: Int): String =
    this.filter { it.key.y == y }.toSortedMap(Comparator.comparingInt { it.x }).values.joinToString(separator = "")

/** Returns all symbols at column index [x], ordered by `y`, as a single string. */
fun Map<Point, Char>.columnToString(x: Int): String =
    this.filter { it.key.x == x }.toSortedMap(Comparator.comparingInt { it.y }).values.joinToString(separator = "")