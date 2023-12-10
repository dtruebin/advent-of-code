package icu.trub.aoc.day07

data class Hand(val cards: List<Card>) : Comparable<Hand> {
    val type: HandType
        get() = with(cards.groupingBy { it }.eachCount()) {
            when {
                containsValue(5) -> HandType.FIVE_OF_A_KIND
                containsValue(4) -> HandType.FOUR_OF_A_KIND
                containsValue(3) && containsValue(2) -> HandType.FULL_HOUSE
                containsValue(3) -> HandType.THREE_OF_A_KIND
                count { it.value == 2 } == 2 -> HandType.TWO_PAIR
                containsValue(2) -> HandType.ONE_PAIR
                else -> HandType.HIGH_CARD
            }
        }

    override fun compareTo(other: Hand): Int = when {
        this.type != other.type -> this.type.compareTo(other.type)
        else -> this.cards.compareTo(other.cards)
    }

    companion object {
        fun parse(line: String): Hand = Hand(line.map { "C$it" }.map { Card.valueOf(it) })
    }

}

private operator fun List<Card>.compareTo(other: List<Card>): Int =
    this.zip(other).map { pair -> pair.first.compareTo(pair.second) }.firstOrNull { it != 0 } ?: 0