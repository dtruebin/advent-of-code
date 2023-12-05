package icu.trub.day03

data class Coordinate(val x: Int, val y: Int) {
    fun left(): Coordinate = Coordinate(x - 1, y)
    fun right(): Coordinate = Coordinate(x + 1, y)
    fun up(): Coordinate = Coordinate(x, y - 1)
    fun down(): Coordinate = Coordinate(x, y + 1)
}