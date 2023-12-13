package icu.trub.aoc.day10

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun invert() = when (this) {
        NORTH -> SOUTH
        EAST -> WEST
        SOUTH -> NORTH
        WEST -> EAST
    }
}