package icu.trub.aoc.day03

import icu.trub.aoc.util.Coordinate

class Schematic(val content: Map<Coordinate, Char>) {
    companion object {
        fun parse(strings: List<String>): Schematic {
            val content = mutableMapOf<Coordinate, Char>()
            for ((y, string) in strings.withIndex()) {
                for ((x, char) in string.withIndex()) {
                    content[Coordinate(x, y)] = char
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

    fun getAdjacentPartNumbers(coordinate: Coordinate): List<Int> {
        val result = mutableListOf<Int?>()

        if (content[coordinate.up()]?.isDigit() == true) {
            // straight above
            result.add(expandSelection(coordinate.up()))
        } else {
            // above left
            result.add(expandSelection(coordinate.up().left()))
            // above right
            result.add(expandSelection(coordinate.up().right()))
        }

        if (content[coordinate.down()]?.isDigit() == true) {
            // straight below
            result.add(expandSelection(coordinate.down()))
        } else {
            // below left
            result.add(expandSelection(coordinate.down().left()))
            // below right
            result.add(expandSelection(coordinate.down().right()))
        }

        result.add(expandSelection(coordinate.left()))
        result.add(expandSelection(coordinate.right()))

        return result.filterNotNull()
    }

    private fun expandSelection(startingCoordinate: Coordinate): Int? {
        if (content[startingCoordinate]?.isDigit() == false) {
            return null
        }

        var curX: Int = startingCoordinate.x
        val curY: Int = startingCoordinate.y

        // rewind left till first non-digit
        while (content[Coordinate(curX, curY)]?.isDigit() == true) {
            curX--
        }

        // rewind right & append digits
        return buildString {
            while (content[Coordinate(++curX, curY)]?.isDigit() == true) {
                append(content[Coordinate(curX, curY)])
            }
        }.toInt()
    }
}