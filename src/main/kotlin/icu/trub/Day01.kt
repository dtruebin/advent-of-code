package icu.trub

fun main() {
    val sum = object {}.javaClass.getResourceAsStream("day01.txt")!!.bufferedReader().lines()
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()
    println(sum)
}

internal fun getDoubleDigitFromFirstAndLast(line: String): Int {
    return "${line.first(Char::isDigit)}${line.last(Char::isDigit)}".toInt()
}