package icu.trub.day02

import icu.trub.AbstractDay
import icu.trub.AocUtil

class Day02(inputFileName: String) : AbstractDay(inputFileName) {
    companion object {
        val bagContent = mapOf(
            Color.RED to 12,
            Color.GREEN to 13,
            Color.BLUE to 14
        )
    }

    override fun solve(): List<Int> = listOf(
        solveDay2a(bagContent),
        solveDay2b()
    )

    private fun solveDay2a(bagContent: Map<Color, Int>): Int {
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

    private fun solveDay2b(): Int = parseGames(inputFileName).sumOf(Game::getPowerOfMinimumCubeSet)

    private fun parseGames(inputFileName: String): List<Game> {
        return AocUtil.readTxtResource(inputFileName)
            .map { line -> Game.parse(line) }
            .toList()
    }
}
