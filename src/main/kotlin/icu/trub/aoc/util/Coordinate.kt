package icu.trub.aoc.util

data class Coordinate(val x: Int, val y: Int) {
    fun left() = Coordinate(x - 1, y)
    fun right() = Coordinate(x + 1, y)
    fun up() = Coordinate(x, y - 1)
    fun down() = Coordinate(x, y + 1)
}