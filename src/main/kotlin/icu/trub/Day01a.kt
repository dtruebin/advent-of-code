package icu.trub

fun main() {
    println(solveDay1a("day01.txt"))
}

fun solveDay1a(inputFileName: String): Int =
    object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()
