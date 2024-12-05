package icu.trub.aoc.day05

import icu.trub.aoc.AocUtil.readTxtResource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AlmanacTest {
    @Test
    fun testParseSeeds() {
        assertEquals(listOf<Long>(79, 14, 55, 13), Almanac.parseSeeds("seeds: 79 14 55 13"))
    }

    @Test
    fun testParse() {
        val parsed = Almanac.parse(this.readTxtResource("day05-test.txt").toList())
        assertEquals(4, parsed.seeds.size)
        assertEquals(7, parsed.maps.size)
    }

}