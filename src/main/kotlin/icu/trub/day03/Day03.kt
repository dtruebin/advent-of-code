package icu.trub.day03

import icu.trub.AocUtil.readTxtResource

fun main() {
    println("Day 3: ${solveDay3a("day03.txt")}")
}

fun solveDay3a(inputFileName: String): Int {
    val schematic = Schematic.parse(readTxtResource(inputFileName).toList())
    return schematic.scanForPartNumbers().sum()
}