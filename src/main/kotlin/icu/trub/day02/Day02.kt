package icu.trub.day02

import icu.trub.AocUtil
import icu.trub.day02.Color.*

val bagContent = mapOf(
    RED to 12,
    GREEN to 13,
    BLUE to 14
)

fun main() {
    println("Day 2: ${solveDay2a(bagContent, "day02.txt")}")
}

fun solveDay2a(bagContent: Map<Color, Int>, inputFileName: String): Int {
    var result = 0

    val games = AocUtil.readTxtResource(inputFileName)
        .map { line -> Game.parse(line) }
        .toList()

    val idToCombinedOutcome = buildMap {
        games.forEach { this[it.id] = it.getCombinedOutcome() }
    }

    for ((id, combinedOutcome) in idToCombinedOutcome) {
        var gameIsPossible = true
        for ((color, totalCubesOfColor) in bagContent) {
            if ((combinedOutcome[color] ?: 0) > totalCubesOfColor) {
                gameIsPossible = false
            }
        }
        if (gameIsPossible) result += id
    }

    return result
}

enum class Color {
    RED, GREEN, BLUE
}
