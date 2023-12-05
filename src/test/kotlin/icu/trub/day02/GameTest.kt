package icu.trub.day02

import icu.trub.day02.Color.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private const val testGameString = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"
val testGame = Game(
    4, listOf(
        mapOf(RED to 3, GREEN to 1, BLUE to 6),
        mapOf(RED to 6, GREEN to 3),
        mapOf(RED to 14, GREEN to 3, BLUE to 15)
    )
)

class GameTest {
    @Test
    fun testParse() {
        assertEquals(testGame, Game.parse(testGameString))
    }

    @ParameterizedTest
    @MethodSource
    fun testGetCombinedOutcome(game: Game, expectedOutput: Map<Color, Int>) {
        assertEquals(expectedOutput, game.getCombinedOutcome())
    }

    @Test
    fun testGetPowerOfMinimumCubeSet() {
        assertEquals(14 * 3 * 15, testGame.getPowerOfMinimumCubeSet())
    }

    companion object {
        @JvmStatic
        fun testGetCombinedOutcome(): Stream<Arguments> = Stream.of(
            arguments(testGame, mapOf(RED to 14, GREEN to 3, BLUE to 15)),
            arguments(Game(1, listOf(mapOf(RED to 1))), mapOf(RED to 1, GREEN to 0, BLUE to 0)),
        )
    }
}