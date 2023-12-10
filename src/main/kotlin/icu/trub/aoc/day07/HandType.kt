package icu.trub.aoc.day07

enum class HandType {
    // Ordered by strength, lowest to highest
    HIGH_CARD,       // 23456
    ONE_PAIR,        // A23A4
    TWO_PAIR,        // 23432
    THREE_OF_A_KIND, // TTT98
    FULL_HOUSE,      // 23332
    FOUR_OF_A_KIND,  // AA8AA
    FIVE_OF_A_KIND,  // AAAAA
}