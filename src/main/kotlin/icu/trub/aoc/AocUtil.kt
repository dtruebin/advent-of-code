package icu.trub.aoc

import java.time.Month
import java.time.YearMonth
import java.util.stream.Stream

object AocUtil {
    private val wordToDigitMap: Map<String, String> = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    private val digitsRegex = (wordToDigitMap.keys.joinToString(separator = "|") + "|\\d").toRegex()

    fun getDoubleDigitFromFirstAndLast(line: String): Int {
        return "${line.first(Char::isDigit)}${line.last(Char::isDigit)}".toInt()
    }

    fun collectDigitsFromLine(line: String): String {
        val result = mutableListOf<String>()
        var startIndex = 0
        while (true) {
            val find = digitsRegex.find(line, startIndex = startIndex) ?: break
            result += wordToDigitMap[find.value] ?: find.value
            startIndex = find.range.first + 1
        }
        return result.joinToString(separator = "")
    }

    /**
     * @return stream of lines in the file
     */
    fun readTxtResource(inputFileName: String): Stream<String> =
        object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()

    private val daysInDecember = YearMonth.of(2023, Month.DECEMBER).lengthOfMonth()

    /**
     * @return sequence of all available concrete [AbstractDay] instances, created using `"day<N>.txt"` as the argument.
     */
    fun createAllDays() = (daysInDecember downTo 1).asSequence().map { createDay(it) }.filterNotNull()

    private fun createDay(n: Int): AbstractDay? {
        val s = n.toString().padStart(2, '0')
        return try {
            val clazz = Class.forName("${this::class.java.packageName}.day$s.Day$s")
            clazz.declaredConstructors[0].newInstance("${clazz.simpleName.lowercase()}.txt") as AbstractDay
        } catch (_: ClassNotFoundException) {
            null
        } catch (t: Throwable) {
            System.err.println(t)
            null
        }
    }

    fun AbstractDay.trySolve(): List<Int?>? = try {
        solve()
    } catch (e: Throwable) {
        null
    }
}