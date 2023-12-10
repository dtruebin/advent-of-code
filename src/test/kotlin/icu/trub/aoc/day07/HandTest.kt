package icu.trub.aoc.day07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class HandTest {
    @Test
    fun testParse() {
        val expected = Hand(listOf(Card.CA, Card.CA, Card.C7, Card.C8, Card.C9))
        val parsed = Hand.parse("AA789")
        assertEquals(expected, parsed)
    }

    @Test
    fun testParseWithJokers() {
        val input = "JJ789"
        assertAll(
            {
                assertEquals(
                    Hand(listOf(Card.CJ, Card.CJ, Card.C7, Card.C8, Card.C9)),
                    Hand.parse(input)
                )
            },
            {
                assertEquals(
                    Hand(listOf(Card.CJJ, Card.CJJ, Card.C7, Card.C8, Card.C9)),
                    Hand.parse(input, withJokers = true)
                )
            }
        )

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

    @ParameterizedTest
    @CsvSource(
        "77777, FIVE_OF_A_KIND",
        "7777J, FIVE_OF_A_KIND",
        "777JJ, FIVE_OF_A_KIND",
        "77JJJ, FIVE_OF_A_KIND",
        "7JJJJ, FIVE_OF_A_KIND",
        "JJJJJ, FIVE_OF_A_KIND",
        "Q7777, FOUR_OF_A_KIND",
        "Q777J, FOUR_OF_A_KIND",
        "Q77JJ, FOUR_OF_A_KIND",
        "Q7JJJ, FOUR_OF_A_KIND",
        "QQ777, FULL_HOUSE",
        "QQ77J, FULL_HOUSE",
        "228JK, THREE_OF_A_KIND",
        "38AJ8, THREE_OF_A_KIND",
        "433J9, THREE_OF_A_KIND",
        "79J6J, THREE_OF_A_KIND",
        "A9J8A, THREE_OF_A_KIND",
        "J228A, THREE_OF_A_KIND",
        "T8JJQ, THREE_OF_A_KIND",
        "TAJA2, THREE_OF_A_KIND",
        "TTT98, THREE_OF_A_KIND",
        "23432, TWO_PAIR",
        "KK677, TWO_PAIR",
        "32T3K, ONE_PAIR",
        "62J8K, ONE_PAIR",
        "8J29Q, ONE_PAIR",
        "A23A4, ONE_PAIR",
        "J425K, ONE_PAIR",
        "23456, HIGH_CARD",
        // <3 https://www.reddit.com/r/adventofcode/comments/18cr4xr/2023_day_7_better_example_input_not_a_spoiler/
        "2AAAA, FOUR_OF_A_KIND",
        "2JJJJ, FIVE_OF_A_KIND",
        "JAAAA, FIVE_OF_A_KIND",
        "JJJJ2, FIVE_OF_A_KIND",
        "JJJ34, FOUR_OF_A_KIND",
    )
    fun testGetTypeWithJokers(handStr: String, expectedType: HandType) {
        val hand = Hand.parse(handStr, withJokers = true)
        assertEquals(expectedType, hand.type)
    }

    @ParameterizedTest
    @CsvSource("33332, 2AAAA", "77888, 77788")
    fun testCompare(greaterHandStr: String, smallerHandString: String) {
        with(Hand) { assertTrue(parse(greaterHandStr) > parse(smallerHandString)) }
    }

    @ParameterizedTest
    @CsvSource(
        "QQQQ2, JKKK2",
        "KTJJT, QQQJA",
        "QQQJA, T55J5",
        "T55J5, KK677",
        "KK677, 32T3K",
        // <3 https://www.reddit.com/r/adventofcode/comments/18cr4xr/2023_day_7_better_example_input_not_a_spoiler/
        "J345A, 2345A",
        "JJJJ2, 2AAAA",
        "2JJJJ, 2AAAA",
        "2JJJJ, JAAAA",
    )
    fun testCompareWithJokers(greaterHandStr: String, smallerHandString: String) {
        with(Hand) { assertTrue(parse(greaterHandStr, true) > parse(smallerHandString, true)) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["33332", "23456", "KTJJT", "QQQJA"])
    fun testCompare_equal(handStr: String) {
        listOf(false, true).forEach { withJokers ->
            assertTrue(Hand.parse(handStr, withJokers).compareTo(Hand.parse(handStr, withJokers)) == 0)
        }
    }
}