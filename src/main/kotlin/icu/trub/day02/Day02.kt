package icu.trub.day02

import icu.trub.AocUtil
import icu.trub.day02.Color.*

val bagContent = mapOf(
    RED to 12,
    GREEN to 13,
    BLUE to 14
)

fun main() {
    println("Day 2, Part One: ${solveDay2a(bagContent, "day02.txt")}")
    println("Day 2, Part Two: ${solveDay2b("day02.txt")}")
}

fun solveDay2a(bagContent: Map<Color, Int>, inputFileName: String): Int {
    var result = 0

    val games = parseGames(inputFileName)

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

fun solveDay2b(inputFileName: String): Int = parseGames(inputFileName).sumOf(Game::getPowerOfMinimumCubeSet)

private fun parseGames(inputFileName: String): List<Game> {
    return AocUtil.readTxtResource(inputFileName)
        .map { line -> Game.parse(line) }
        .toList()
}

enum class Color {
    RED, GREEN, BLUE
}
