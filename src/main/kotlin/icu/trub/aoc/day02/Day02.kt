package icu.trub.aoc.day02

import icu.trub.aoc.AbstractDay
import icu.trub.aoc.AocUtil

class Day02(inputFileName: String) : AbstractDay(inputFileName) {
    private val games: List<Game> = AocUtil.readTxtResource(inputFileName)
        .map { line -> Game.parse(line) }
        .toList()

    override fun solvePartOne(): Int {
        var result = 0

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

    override fun solvePartTwo(): Int = games.sumOf(Game::getPowerOfMinimumCubeSet)

    companion object {
        val bagContent = mapOf(
            Color.RED to 12,
            Color.GREEN to 13,
            Color.BLUE to 14
        )
    }
}
