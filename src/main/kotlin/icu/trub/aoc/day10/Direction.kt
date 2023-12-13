package icu.trub.aoc.day10

import icu.trub.aoc.day10.Direction.*
import icu.trub.aoc.util.Coordinate

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun invert() = when (this) {
        NORTH -> SOUTH
        EAST -> WEST
        SOUTH -> NORTH
        WEST -> EAST
    }
}

fun Coordinate.shiftTo(direction: Direction): Coordinate = when (direction) {
    NORTH -> up()
    EAST -> right()
    SOUTH -> down()
    WEST -> left()
}