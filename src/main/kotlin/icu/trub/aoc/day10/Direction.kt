package icu.trub.aoc.day10

import icu.trub.aoc.day10.Direction.*
import icu.trub.aoc.util.Point

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun invert() = when (this) {
        NORTH -> SOUTH
        EAST -> WEST
        SOUTH -> NORTH
        WEST -> EAST
    }
}

fun Point.shiftTo(direction: Direction): Point = when (direction) {
    NORTH -> up()
    EAST -> right()
    SOUTH -> down()
    WEST -> left()
}