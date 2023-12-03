package icu.trub

fun main() {
    val sum = object {}.javaClass.getResourceAsStream("day01.txt")!!.bufferedReader().lines()
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()
    println(sum)
}

