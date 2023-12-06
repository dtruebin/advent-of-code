package icu.trub

import icu.trub.day01.Day01
import icu.trub.day02.Day02
import icu.trub.day03.Day03

fun main() {
    listOf(
        Day01("day01.txt"),
        Day02("day02.txt"),
        Day03("day03.txt"),
    ).forEach {
        with(it) { println("${javaClass.simpleName} solutions: ${solve()}") }
    }
}