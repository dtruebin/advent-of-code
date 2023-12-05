package icu.trub.day03

import icu.trub.AocUtil.readTxtResource

fun main() {
    println("Day 3, Part One: ${solveDay3a("day03.txt")}")
    println("Day 3, Part Two: ${solveDay3b("day03.txt")}")
}

fun solveDay3a(inputFileName: String): Int = parse(inputFileName).scanForPartNumbers().sum()
fun solveDay3b(inputFileName: String): Int = parse(inputFileName).scanForGearRatios().sum()
private fun parse(inputFileName: String) = Schematic.parse(readTxtResource(inputFileName).toList())