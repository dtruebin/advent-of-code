package icu.trub.aoc.day10

class Field(private val matrix: Map<Coordinate, Char>) {
    operator fun get(x: Int, y: Int): Char? = matrix[Coordinate(x, y)]
    internal fun findAnimal(): Coordinate = matrix.filterValues { it == ANIMAL }.keys.single()

    fun findLoop(start: Coordinate = findAnimal()): List<Pair<Coordinate, Char>> = buildList {
        add(start to matrix[start]!!)
        var cursor: Coordinate = start
        var foundAt: Direction? = null
        while (cursor != start || size == 1) {
            val lastCameFrom = foundAt?.invert()
            val (direction, char) = findNext(cursor, exclude = lastCameFrom) ?: break
            foundAt = direction
            cursor = cursor.shiftTo(foundAt)
            add(cursor to char)
        }
    }

    /**
     * Checks coordinates adjacent to the [current] one, looking for a pipe to connect to.
     * @param current coordinate whose adjacent coordinates will be checked
     * @param exclude direction to be excluded from checking. Allows to avoid checking the same direction
     *        we potentially came from
     * @return a tuple of [Direction] relative to the current coordinate where a pipe was found, and
     *         the char representing the pipe itself; `null` if nothing could be found
     */
    internal fun findNext(current: Coordinate, exclude: Direction? = null): Pair<Direction, Char>? {
        val outgoingDirections = Pipe.by(matrix[current]!!)?.connectsTo ?: Direction.entries
        val possibleDirections = outgoingDirections.filter { it != exclude }
        for (direction in possibleDirections) {
            val char = matrix[current.shiftTo(direction)] ?: continue
            val foundPipe = Pipe.by(char) ?: continue
            if (canConnectTo(foundPipe).towards(direction)) {
                return direction to char
            }
        }
        return null
    }

    // DSL time :D
    private fun canConnectTo(pipe: Pipe): Pipe = pipe
    private fun Pipe.towards(direction: Direction) = this.connectsTo.contains(direction.invert())

    companion object {
        private const val GROUND = '.'
        internal const val ANIMAL = 'S'
        fun parse(input: List<String>): Field = Field(buildMap {
            for ((row, line) in input.withIndex()) {
                for ((col, char) in line.withIndex()) {
                    if (char != GROUND) put(Coordinate(col, row), char)
                }
            }
        })
    }
}