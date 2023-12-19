package icu.trub.aoc.day13


import icu.trub.aoc.util.Point

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
        yield(lookForHorizontalReflection())
        yield(lookForVerticalReflection())
    }.firstNotNullOf { it }

    fun getHorizontalLine(y: Int): String = matrix.filter { it.key.y == y }.values.joinToString(separator = "")
    fun getVerticalLine(x: Int): String = matrix.filter { it.key.x == x }.values.joinToString(separator = "")

    private fun lookForVerticalReflection(): MirrorReflection? {
        val pairStartingColumnIndices = (0..lastColIndex).asSequence()
            .map { getVerticalLine(it) }
            .zipWithNext()
            .withIndex()
            .filter { it.value.first == it.value.second }
            .map { it.index }
            .toList()
            .also { if (it.isEmpty()) return null }

        val symmetryIndices = pairStartingColumnIndices.map {
            var left = it
            var right = it + 1
            while (getVerticalLine(left) == getVerticalLine(right)) {
                left--
                right++
            }
            it to (left to right)
        }.filter { it.second.first < 0 || it.second.second > lastColIndex }
            .map { it.first }

        val winningIndex = with(symmetryIndices) { if (isNotEmpty()) last() else return null }
        return LeftRightReflection(winningIndex + 1)
    }

    private fun lookForHorizontalReflection(): MirrorReflection? {
        val pairStartingRowIndices = (0..lastRowIndex).asSequence()
            .map { getHorizontalLine(it) }
            .zipWithNext()
            .withIndex()
            .filter { it.value.first == it.value.second }
            .map { it.index }
            .toList()
            .also { if (it.isEmpty()) return null }

        val symmetryIndices = pairStartingRowIndices.map {
            var up = it
            var down = it + 1
            while (getHorizontalLine(up) == getHorizontalLine(down)) {
                up--
                down++
            }
            it to (up to down)
        }.filter { it.second.first < 0 || it.second.second > lastRowIndex }
            .map { it.first }

        val winningIndex = with(symmetryIndices) { if (isNotEmpty()) last() else return null }
        return UpDownReflection(winningIndex + 1)
    }

    fun getSummary(): Int {
        return when (reflection) {
            is LeftRightReflection -> reflection.coordinate
            is UpDownReflection -> reflection.coordinate * 100
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