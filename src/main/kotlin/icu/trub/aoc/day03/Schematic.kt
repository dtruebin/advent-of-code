package icu.trub.aoc.day03

import icu.trub.aoc.util.Point

class Schematic(val content: Map<Point, Char>) {
    companion object {
        fun parse(strings: List<String>): Schematic {
            val content = mutableMapOf<Point, Char>()
            for ((y, string) in strings.withIndex()) {
                for ((x, char) in string.withIndex()) {
                    content[Point(x, y)] = char
                }
            }
            return Schematic(content)
        }
    }

    fun scanForPartNumbers(): List<Int> {
        val result = mutableListOf<Int>()
        for ((coordinate, char) in content) {
            if (!char.isDigit() && char != '.') {
                result.addAll(getAdjacentPartNumbers(coordinate))
            }
        }
        return result
    }

    fun scanForGearRatios(): List<Int> {
        val result = mutableListOf<Int>()
        for ((coordinate, char) in content) {
            if (char == '*') {
                val adjacentPartNumbers = getAdjacentPartNumbers(coordinate)
                if (adjacentPartNumbers.size == 2) {
                    result.add(adjacentPartNumbers[0] * adjacentPartNumbers[1])
                }
            }
        }
        return result
    }

    fun getAdjacentPartNumbers(point: Point): List<Int> {
        val result = mutableListOf<Int?>()

        if (content[point.up()]?.isDigit() == true) {
            // straight above
            result.add(expandSelection(point.up()))
        } else {
            // above left
            result.add(expandSelection(point.up().left()))
            // above right
            result.add(expandSelection(point.up().right()))
        }

        if (content[point.down()]?.isDigit() == true) {
            // straight below
            result.add(expandSelection(point.down()))
        } else {
            // below left
            result.add(expandSelection(point.down().left()))
            // below right
            result.add(expandSelection(point.down().right()))
        }

        result.add(expandSelection(point.left()))
        result.add(expandSelection(point.right()))

        return result.filterNotNull()
    }

    private fun expandSelection(startingPoint: Point): Int? {
        if (content[startingPoint]?.isDigit() == false) {
            return null
        }

        var curX: Int = startingPoint.x
        val curY: Int = startingPoint.y

        // rewind left till first non-digit
        while (content[Point(curX, curY)]?.isDigit() == true) {
            curX--
        }

        // rewind right & append digits
        return buildString {
            while (content[Point(++curX, curY)]?.isDigit() == true) {
                append(content[Point(curX, curY)])
            }
        }.toInt()
    }
}