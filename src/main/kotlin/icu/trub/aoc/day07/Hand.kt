package icu.trub.aoc.day07

data class Hand(val cards: List<Card>) : Comparable<Hand> {
    val type: HandType
        get() = with(cards.groupingBy { it }.eachCount()) {
            if (containsKey(Card.CJJ)) {
                val jokerCount = this[Card.CJJ]
                when {
                    containsValue(5) -> HandType.FIVE_OF_A_KIND
                    containsValue(4) && jokerCount == 1 -> HandType.FIVE_OF_A_KIND
                    containsValue(3) && jokerCount == 2 -> HandType.FIVE_OF_A_KIND
                    containsValue(2) && jokerCount == 3 -> HandType.FIVE_OF_A_KIND
                    jokerCount == 4 -> HandType.FIVE_OF_A_KIND

                    containsValue(4) -> HandType.FOUR_OF_A_KIND
                    containsValue(3) && jokerCount == 1 -> HandType.FOUR_OF_A_KIND
                    count { it.value == 2 } == 2 && jokerCount == 2 -> HandType.FOUR_OF_A_KIND
                    jokerCount == 3 -> HandType.FOUR_OF_A_KIND

                    containsValue(3) && containsValue(2) -> HandType.FULL_HOUSE
                    count { it.value == 2 } == 2 && jokerCount == 1 -> HandType.FULL_HOUSE

                    containsValue(3) -> HandType.THREE_OF_A_KIND
                    containsValue(2) && jokerCount == 1 -> HandType.THREE_OF_A_KIND
                    jokerCount == 2 -> HandType.THREE_OF_A_KIND

                    else -> HandType.ONE_PAIR
                }
            } else when {
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
        fun parse(line: String, withJokers: Boolean = false): Hand = Hand(
            line.map { "C$it" }.map { Card.valueOf(it) }.map {
                when (it == Card.CJ && withJokers) {
                    true -> Card.CJJ
                    false -> it
                }
            }
        )
    }

}

private operator fun List<Card>.compareTo(other: List<Card>): Int =
    this.zip(other).map { pair -> pair.first.compareTo(pair.second) }.firstOrNull { it != 0 } ?: 0