package icu.trub

import icu.trub.AocUtil.collectDigitsFromLine
import icu.trub.AocUtil.getDoubleDigitFromFirstAndLast
import icu.trub.AocUtil.readTxtResource

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
        .map(::collectDigitsFromLine)
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()