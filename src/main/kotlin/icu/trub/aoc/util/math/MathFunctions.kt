package icu.trub.aoc.util.math

import icu.trub.aoc.util.Point
import kotlin.math.absoluteValue

/**
 * @return area of the polygon using shoelace formula
 */
fun getPolygonArea(vertices: List<Point>): Double = vertices
    .zipWithNext()
    .toMutableList()
    .also { it.add(vertices.last() to vertices.first()) }
    .sumOf { it.first.x * it.second.y - it.first.y * it.second.x }
    .absoluteValue
    .div(2.0)

/**
 * @return a sequence of all combinations of receiver collection's values (all sizes, including zero-size combination)
 * @author https://rosettacode.org/wiki/Power_set#Kotlin
 */
fun <T> Set<T>.subsets(): Sequence<Set<T>> = sequence {
    when (size) {
        0 -> yield(emptySet<T>())
        else -> {
            val head = first()
            val tail = this@subsets - head
            yieldAll(tail.subsets())
            for (subset in tail.subsets()) {
                yield(setOf(head) + subset)
            }
        }
    }
}