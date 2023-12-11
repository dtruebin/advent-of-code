package icu.trub.aoc.day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NetworkTest {
    private val network = Network.parse(
        """AAA = (BBB, BBB)
           BBB = (AAA, ZZZ)
           ZZZ = (ZZZ, ZZZ)""".lines()
    )

    @Test
    fun testParse() {
        assertEquals(3, network.nodes.size)
        assertEquals(Pair("BBB", "BBB"), network.nodes["AAA"])
        assertEquals(Pair("AAA", "ZZZ"), network.nodes["BBB"])
        assertEquals(Pair("ZZZ", "ZZZ"), network.nodes["ZZZ"])
    }

    @Test
    fun testNavigate() {
        val hops = network.navigate("AAA", "ZZZ", "LLR")
        assertEquals(6, hops)
    }
}