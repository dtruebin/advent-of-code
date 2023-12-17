package icu.trub.aoc.util.string

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StringFunctionsKtTest {
    @Test
    fun replaceCharAtIndices() {
        assertEquals("#b##", "abcd".replaceCharAtIndices(listOf(0, 2, 2, 3), '#'))
    }
}