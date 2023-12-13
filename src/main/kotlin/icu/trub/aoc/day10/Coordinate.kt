package icu.trub.aoc.day10

import icu.trub.aoc.day10.Direction.*

data class Coordinate(val x: Int, val y: Int) {
    fun shiftTo(direction: Direction): Coordinate = when (direction) {
        NORTH -> up()
        EAST -> right()
        SOUTH -> down()
        WEST -> left()
    }

    private fun left(): Coordinate = Coordinate(x - 1, y)
    private fun right(): Coordinate = Coordinate(x + 1, y)
    private fun up(): Coordinate = Coordinate(x, y - 1)
    private fun down(): Coordinate = Coordinate(x, y + 1)
}