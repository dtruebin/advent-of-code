package icu.trub.aoc.day10

import icu.trub.aoc.util.Coordinate

class Field(private val matrix: Map<Coordinate, Char>) {
    operator fun get(x: Int, y: Int): Char? = matrix[Coordinate(x, y)]
    internal fun findAnimal(): Coordinate = matrix.filterValues { it == ANIMAL }.keys.single()

    fun findLoop(start: Coordinate = findAnimal()): List<Coordinate> = buildList {
        var cursor: Coordinate = start
        var foundAt: Direction? = null
        do {
            val lastCameFrom = foundAt?.invert()
            foundAt = findNextDirection(cursor, exclude = lastCameFrom) ?: break
            cursor = cursor.shiftTo(foundAt)
            add(cursor)
        } while (start !in this || size == 0)
    }

    /**
     * Checks coordinates adjacent to the [current] one, looking for a pipe to connect to.
     * @param current coordinate whose adjacent coordinates will be checked
     * @param exclude direction to be excluded from checking. Allows to avoid checking the same direction
     *        we potentially came from
     * @return the [Direction] relative to the current coordinate where a connecting pipe was found,
     *         or `null` if nothing could be found
     */
    internal fun findNextDirection(current: Coordinate, exclude: Direction? = null): Direction? {
        val outgoingDirections = Pipe.by(matrix[current]!!)?.connectsTo ?: Direction.entries
        val possibleDirections = outgoingDirections.filter { it != exclude }
        for (direction in possibleDirections) {
            val char = matrix[current.shiftTo(direction)] ?: continue
            val foundPipe = when (char) {
                ANIMAL -> return direction // closing the loop, so definitely can connect to this pipe
                else -> Pipe.by(char) ?: continue
            }
            if (canConnectTo(foundPipe).towards(direction)) {
                return direction
            }
        }
        return null
    }

    // DSL time :D
    private fun canConnectTo(pipe: Pipe): Pipe = pipe
    private fun Pipe.towards(direction: Direction) = this.connectsTo.contains(direction.invert())

    companion object {
        internal const val ANIMAL = 'S'
        fun parse(input: List<String>): Field = Field(buildMap {
            input.forEachIndexed { row, line ->
                line.forEachIndexed { col, char ->
                    put(Coordinate(col, row), char)
                }
            }
        })
    }
}