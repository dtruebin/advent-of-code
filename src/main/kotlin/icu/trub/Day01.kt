package icu.trub

import java.util.stream.Stream

fun main() {
    println("Day 1, part 1: ${solveDay1a("day01.txt")}")
    println("Day 1, part 2: ${solveDay1b("day01.txt")}")
}

fun solveDay1a(inputFileName: String): Int =
    readTxtResource(inputFileName)
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()

fun solveDay1b(inputFileName: String): Int =
    readTxtResource(inputFileName)
        .map(::replaceWordsWithDigits)
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()

private fun readTxtResource(inputFileName: String): Stream<String> =
    object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()