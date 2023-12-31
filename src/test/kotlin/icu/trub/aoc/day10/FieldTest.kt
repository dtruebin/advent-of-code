package icu.trub.aoc.day10

import icu.trub.aoc.day10.Direction.*
import icu.trub.aoc.day10.Field.Companion.ANIMAL
import icu.trub.aoc.util.Point
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class FieldTest {
    private val input = """
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...""".trimIndent().lines()

    private val field = Field.parse(input)

    private val animalCoordinates = Point(0, 2)

    @Test
    fun testParse() {
        assertAll(
            { assertEquals(null, field[100, 100]) },
            { assertEquals('.', field[0, 0]) },
            { assertEquals(ANIMAL, field[0, 2]) },
            { assertEquals('J', field[1, 2]) },
            { assertEquals('7', field[4, 2]) },
        )
    }

    @Test
    fun testFindAnimal() {
        assertEquals(animalCoordinates, field.findAnimal())
    }

    @Test
    fun testFindNext() {
        assertAll(
            { assertEquals(EAST /*to 'J'*/, field.findNextDirection(animalCoordinates)) },
            { assertEquals(NORTH /*to '|'*/, field.findNextDirection(Point(3, 2))) },
            { assertEquals(EAST /*to '7'*/, field.findNextDirection(Point(3, 2), exclude = NORTH)) },
            { assertEquals(SOUTH /*to '|'*/, field.findNextDirection(Point(3, 0), exclude = WEST)) },
        )
    }
}