package icu.trub.aoc

import icu.trub.aoc.AocUtil.createAllDays
import icu.trub.aoc.AocUtil.trySolve

fun main() {
    createAllDays().forEach {
        with(it) { println("${javaClass.simpleName} solutions: ${trySolve() ?: "unavailable"}") }
    }
}