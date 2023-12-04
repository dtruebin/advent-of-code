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

val digitWordsRegex = wordToDigitMap.keys.joinToString(separator = "|").toRegex()

fun replaceWordsWithDigits(line: String): String {
    var result = line
    while (true) {
        val match = digitWordsRegex.find(result)?.value ?: break
        result = result.replaceFirst(match, wordToDigitMap[match]!!)
    }
    return result
}