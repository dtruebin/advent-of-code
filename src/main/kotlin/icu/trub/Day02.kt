package icu.trub

fun main() {
    println(solve("day01.txt"))
}

fun solve(inputFileName: String): Int =
    object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()
        .map(::replaceWordsWithDigits)
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()