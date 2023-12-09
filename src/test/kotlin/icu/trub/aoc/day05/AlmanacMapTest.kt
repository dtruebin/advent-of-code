package icu.trub.aoc.day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AlmanacMapTest {
    @Test
    fun testRangeDescriptor() {
        val descriptor = AlmanacMap.RangeDescriptor(98, 50, 2)
        assertEquals(98L..99L, descriptor.srcRange)
    }

    @Test
    fun testGet() {
        val map = AlmanacMap(
            listOf(
                AlmanacMap.RangeDescriptor(98, 50, 2),
                AlmanacMap.RangeDescriptor(50, 52, 48)
            )
        )
        assertEquals(51, map.get(99))
        assertEquals(55, map.get(53))
        assertEquals(10, map.get(10))
    }
}