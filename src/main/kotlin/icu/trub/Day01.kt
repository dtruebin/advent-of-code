package icu.trub

fun main() {
    println(solveDay1("day01.txt"))
}

fun solveDay1(inputFileName: String): Int =
    object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()
