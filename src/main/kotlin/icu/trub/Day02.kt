package icu.trub

fun main() {
    println(solveDay2("day01.txt"))
}

fun solveDay2(inputFileName: String): Int =
    object {}.javaClass.getResourceAsStream(inputFileName)!!.bufferedReader().lines()
        .map(::replaceWordsWithDigits)
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()