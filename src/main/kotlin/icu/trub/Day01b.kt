package icu.trub

fun main() {
    println(solveDay1b("day01.txt"))
}

fun solveDay1b(inputFileName: String): Int =
    object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()
        .map(::replaceWordsWithDigits)
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()