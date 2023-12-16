package icu.trub.aoc.util

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    fun left() = Point(x - 1, y)
    fun right() = Point(x + 1, y)
    fun up() = Point(x, y - 1)
    fun down() = Point(x, y + 1)
    infix fun distanceTo(other: Point): Int = abs(x - other.x) + abs(y - other.y)
}