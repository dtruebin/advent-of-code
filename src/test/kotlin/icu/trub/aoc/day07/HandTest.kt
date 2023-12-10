package icu.trub.aoc.day07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class HandTest {
    @Test
    fun testParse() {
        val expected = Hand(listOf(Card.CA, Card.CA, Card.C7, Card.C8, Card.C9))
        val parsed = Hand.parse("AA789")
        assertEquals(expected, parsed)
    }

    @ParameterizedTest
    @CsvSource(
        "AAAAA, FIVE_OF_A_KIND",
        "AA8AA, FOUR_OF_A_KIND",
        "23332, FULL_HOUSE",
        "TTT98, THREE_OF_A_KIND",
        "23432, TWO_PAIR",
        "A23A4, ONE_PAIR",
        "23456, HIGH_CARD"
    )
    fun testGetType(handStr: String, expectedType: HandType) {
        val hand = Hand.parse(handStr)
        assertEquals(expectedType, hand.type)
    }

    @Test
    fun testCompare() {
        with(Hand) {
            assertAll(
                { assertTrue(parse("33332") > parse("2AAAA")) },
                { assertTrue(parse("77888") > parse("77788")) },
                { assertTrue(parse("23456").compareTo(parse("23456")) == 0) },
            )
        }
    }
}