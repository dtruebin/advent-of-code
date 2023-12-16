package icu.trub.aoc.day11

import icu.trub.aoc.AbstractDayTest
import org.junit.jupiter.api.BeforeEach

class Day11Test : AbstractDayTest<Day11>(Day11::class.java) {
    override val expectedSolutions = listOf(374, 8410)

    @BeforeEach
    fun setUp() {
        day.partTwoExpansionFactor = 100
    }
}