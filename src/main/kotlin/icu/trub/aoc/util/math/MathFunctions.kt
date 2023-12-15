package icu.trub.aoc.util.math

import icu.trub.aoc.util.Coordinate
import kotlin.math.absoluteValue

/**
 * @return area of the polygon using shoelace formula
 */
fun getPolygonArea(vertices: List<Coordinate>): Double = vertices
    .zipWithNext()
    .toMutableList()
    .also { it.add(vertices.last() to vertices.first()) }
    .sumOf { it.first.x * it.second.y - it.first.y * it.second.x }
    .absoluteValue
    .div(2.0)