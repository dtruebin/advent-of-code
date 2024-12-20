package icu.trub.aoc

import java.time.Month
import java.time.YearMonth

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
    fun Any.readTxtResource(name: String) =
        this.javaClass.getResourceAsStream(name)?.bufferedReader()?.lineSequence()
            ?: throw IllegalArgumentException("Resource $name not found for class ${this.javaClass.name}")

    private val daysInDecember = YearMonth.of(2023, Month.DECEMBER).lengthOfMonth()

    /**
     * @return sequence of all available concrete [AbstractDay] instances, created using `"day<N>.txt"` as the argument.
     */
    fun createAllDays() = (daysInDecember downTo 1).asSequence().map { createDay(it) }.filterNotNull()

    private fun createDay(n: Int): AbstractDay? {
        val s = n.toString().padStart(2, '0')
        return try {
            val clazz = Class.forName("${this::class.java.packageName}.day$s.Day$s")
            val inputFileName = "${clazz.simpleName.lowercase()}.txt"
            val resourceExists = clazz.getResource(inputFileName)?.path != null
            if (resourceExists) clazz.declaredConstructors[0].newInstance(inputFileName) as AbstractDay else null
        } catch (_: ClassNotFoundException) {
            null
        } catch (t: Throwable) {
            System.err.println("Day$n: ${t.getRootCause()}")
            null
        }
    }

    private fun Throwable.getRootCause(): Throwable {
        var cause: Throwable? = this
        while (cause?.cause != null) {
            cause = cause.cause
        }
        return cause ?: this
    }

    fun AbstractDay.trySolve() = try {
        solve()
    } catch (e: Throwable) {
        e.printStackTrace()
        e.message
    }

    /**
     * Finds least common multiple of numbers in the list (`0` if the list is empty).
     */
    fun findLCM(numbers: List<Long>): Long {
        if (numbers.isEmpty()) return 0
        if (numbers.size == 1) return numbers.single()
        var result = numbers[0]
        for (i in 1..<numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }

    private fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }

    fun IntProgression.zipWithReversed(): List<Pair<Int, Int>> {
        return this.let { it to it.reversed() }.let { it.first.zip(it.second) }
    }
}