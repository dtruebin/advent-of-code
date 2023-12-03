import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day01KtTest {
    @ParameterizedTest
    @MethodSource
    fun testGetDoubleDigitFromFirstAndLast(input: String, expectedOutput: Int) {
        assertEquals(expectedOutput, getDoubleDigitFromFirstAndLast(input))
    }

    companion object {
        @JvmStatic
        fun testGetDoubleDigitFromFirstAndLast(): Stream<Arguments> = Stream.of(
            arguments("12", 12),
            arguments("123", 13),
            arguments("8", 88),
            arguments("14gxqgqsqqbxfpxnbccjc33eight", 13),
        )
    }
}