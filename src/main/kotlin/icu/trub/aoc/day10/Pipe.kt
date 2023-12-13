package icu.trub.aoc.day10

enum class Pipe(private val c: Char, val connectsTo: Set<Direction>) {
    // L left R right U up D down
    NS('|', setOf(Direction.NORTH, Direction.SOUTH)),
    EW('-', setOf(Direction.EAST, Direction.WEST)),
    NE('L', setOf(Direction.NORTH, Direction.EAST)),
    NW('J', setOf(Direction.NORTH, Direction.WEST)),
    SW('7', setOf(Direction.SOUTH, Direction.WEST)),
    SE('F', setOf(Direction.SOUTH, Direction.EAST));

    companion object {
        fun by(c: Char): Pipe? = entries.find { it.c == c }
    }
}