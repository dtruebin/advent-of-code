package icu.trub

fun main() {
    println("Day 1, part 1: ${solveDay1a("day01.txt")}")
    println("Day 1, part 2: ${solveDay1b("day01.txt")}")
}

fun solveDay1a(inputFileName: String): Int =
    object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()

fun solveDay1b(inputFileName: String): Int =
    object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()
        .map(::replaceWordsWithDigits)
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()