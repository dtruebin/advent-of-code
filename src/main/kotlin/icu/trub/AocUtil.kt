package icu.trub

internal fun getDoubleDigitFromFirstAndLast(line: String): Int {
    return "${line.first(Char::isDigit)}${line.last(Char::isDigit)}".toInt()
}

val wordToDigitMap: Map<String, String> = mapOf(
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

val digitsRegex = (wordToDigitMap.keys.joinToString(separator = "|") + "|\\d").toRegex()

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