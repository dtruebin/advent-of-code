package icu.trub

internal fun getDoubleDigitFromFirstAndLast(line: String): Int {
    return "${line.first(Char::isDigit)}${line.last(Char::isDigit)}".toInt()
}