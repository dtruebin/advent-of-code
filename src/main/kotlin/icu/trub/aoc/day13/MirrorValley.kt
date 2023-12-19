package icu.trub.aoc.day13


import icu.trub.aoc.util.Point
import icu.trub.aoc.util.collections.rotate
import icu.trub.aoc.util.columnToString
import icu.trub.aoc.util.rowToString

internal class MirrorValley(val patterns: List<Pattern>) {
    companion object {
        fun parse(input: List<String>): MirrorValley = MirrorValley(buildList {
            val patternLines = mutableListOf<String>()
            for ((index, line) in input.withIndex()) {
                if (line.isNotBlank() && index < input.lastIndex) {
                    patternLines.add(line)
                } else {
                    if (index == input.lastIndex) {
                        patternLines.add(line)
                    }
                    add(Pattern.parse(patternLines))
                    patternLines.clear()
                }
            }
        })
    }
}

class Pattern(val matrix: Map<Point, Char>) {
    val lastColIndex: Int = matrix.maxOf { it.key.x }
    val lastRowIndex: Int = matrix.maxOf { it.key.y }

    val reflection: MirrorReflection = sequence {
        yield(matrix.lookForHorizontalReflection())
        yield(matrix.lookForVerticalReflection())
    }.firstNotNullOf { it }

    val altReflection: MirrorReflection
        get() = sequence {
            (0..lastColIndex).map { x ->
                (0..lastRowIndex).map { y ->
                    val mm = matrix
                        .toMutableMap()
                        .also { it[Point(x, y)] = if (it[Point(x, y)] == '.') '#' else '.' }
                        .toMap()
                    yield(mm.lookForHorizontalReflection(excludeIndex = if (reflection is UpDownReflection) reflection.coordinate - 1 else null))
                    yield(mm.lookForVerticalReflection(excludeIndex = if (reflection is LeftRightReflection) reflection.coordinate - 1 else null))
                }
            }
        }.filter { it != reflection }.firstNotNullOf { it }

    fun getHorizontalLine(y: Int): String = matrix.rowToString(y)
    fun getVerticalLine(x: Int): String = matrix.columnToString(x)

    private fun Map<Point, Char>.lookForVerticalReflection(excludeIndex: Int? = null): MirrorReflection? =
        rotate().lookForHorizontalReflection(excludeIndex)?.let { LeftRightReflection(it.coordinate) }

    private fun Map<Point, Char>.lookForHorizontalReflection(excludeIndex: Int? = null): MirrorReflection? {
        val lastRowIndex: Int = maxOf { it.key.y }
        val pairStartingRowIndices = (0..lastRowIndex).asSequence()
            .map { rowToString(it) }
            .zipWithNext()
            .withIndex()
            .filter { it.value.first == it.value.second }
            .map { it.index }
            .toList()
            .also { if (it.isEmpty()) return null }

        val symmetryIndices = pairStartingRowIndices.map {
            var up = it
            var down = it + 1
            while (rowToString(up) == rowToString(down)) {
                up--
                down++
            }
            it to (up to down)
        }.filter { it.second.first < 0 || it.second.second > lastRowIndex }
            .map { it.first }

        val winningIndex = symmetryIndices.ifEmpty { return null }.lastOrNull { it != excludeIndex } ?: return null

        return UpDownReflection(winningIndex + 1)
    }

    fun getSummary(isClean: Boolean = false): Int = with(if (isClean) altReflection else reflection) {
        return when (this) {
            is LeftRightReflection -> coordinate
            is UpDownReflection -> coordinate * 100
            else -> throw IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return "Pattern(${lastColIndex + 1} by ${lastRowIndex + 1}, 1st line = ${getHorizontalLine(0)})"
    }

    companion object {
        fun parse(input: List<String>): Pattern = Pattern(buildMap {
            input.forEachIndexed { row, line ->
                line.forEachIndexed { col, char -> put(Point(col, row), char) }
            }
        })
    }

    interface MirrorReflection {
        /** Row or column number (index+1) after which reflection starts. */
        val coordinate: Int
    }

    data class LeftRightReflection(override val coordinate: Int) : MirrorReflection
    data class UpDownReflection(override val coordinate: Int) : MirrorReflection
}