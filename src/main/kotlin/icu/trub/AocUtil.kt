package icu.trub

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
}