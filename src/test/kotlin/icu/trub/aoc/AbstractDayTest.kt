package icu.trub.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * Base class for each Day's tests. Creates the concrete [AbstractDay] reflectively, using `day<N>-test.txt` as the argument,
 * and checks the result of [solve][AbstractDay.solve] against the supplied [expectedSolutions].
 */
abstract class AbstractDayTest<D : AbstractDay>(type: Class<D>) {
    abstract val expectedSolutions: List<Int?>
    protected val day: D = type.declaredConstructors[0].newInstance("${type.simpleName.lowercase()}-test.txt") as D

    @Test
    fun testSolve() {
        assertEquals(expectedSolutions.map { it?.toLong() }, day.solve())
    }
}