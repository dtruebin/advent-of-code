package icu.trub.aoc

import icu.trub.aoc.day01.Day01
import icu.trub.aoc.day02.Day02
import icu.trub.aoc.day03.Day03
import icu.trub.aoc.day04.Day04

fun main() {
    listOf(
        Day01("day01.txt"),
        Day02("day02.txt"),
        Day03("day03.txt"),
        Day04("day04.txt"),
    ).forEach {
        with(it) { println("${javaClass.simpleName} solutions: ${solve()}") }
    }
}