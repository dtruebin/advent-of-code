package icu.trub.aoc.util.collections

import icu.trub.aoc.AocUtil.zipWithReversed
import icu.trub.aoc.util.Point

fun Map<Point, Char>.visualize() {
    (0..maxOf { it.key.y }).map { y ->
        (0..maxOf { it.key.x }).map { x ->
            print(this[Point(x, y)])
        }
        println()
    }
}

/** Returns a map rotated 90 degrees, i.e., one where [Point]s are first transposed, then swapped within each row. */
fun Map<Point, Char>.rotate(): Map<Point, Char> = buildMap {
    (0..this@rotate.maxOf { it.key.x }).map { x ->
        (0..this@rotate.maxOf { it.key.y }).zipWithReversed().map { (y, reversedY) ->
            put(Point(reversedY, x), this@rotate[Point(x, y)]!!)
        }
    }
}