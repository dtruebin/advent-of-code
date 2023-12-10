package icu.trub.aoc.day07

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun testCompare() {
        assertTrue(Card.CA > Card.C2)
    }
}