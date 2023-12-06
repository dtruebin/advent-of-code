package icu.trub.aoc.day02

class Game(val id: Int, val outcomes: List<Map<Color, Int>>) {
    companion object {
        /**
         * Example input: `"Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"`.
         */
        fun parse(line: String): Game {
            val idAndOutcomes = line.split(": ")

            val id = idAndOutcomes[0].replace("\\D".toRegex(), "").toInt()

            val outcomes = mutableListOf<Map<Color, Int>>()
            val outcomesStr = idAndOutcomes[1].split("; ")
            for (outcomeStr in outcomesStr) {
                val outcome = mutableMapOf<Color, Int>()
                for (numToColor in outcomeStr.split(", ")) {
                    val (count, color) = numToColor.split(" ")
                    outcome[Color.valueOf(color.uppercase())] = count.toInt()
                }
                outcomes += outcome
            }

            return Game(id, outcomes)
        }
    }

    /**
     * @return map consisting of maximum count among all outcomes, per each color
     */
    fun getCombinedOutcome(): Map<Color, Int> {
        val result = mutableMapOf<Color, Int>()
        Color.entries.forEach { result[it] = 0 }
        for (outcome in outcomes) {
            for ((color, count) in outcome) {
                result.compute(color) { _, knownMax -> maxOf(count, (knownMax ?: 0)) }
            }
        }
        return result.toMap()
    }

    fun getPowerOfMinimumCubeSet(): Int = getCombinedOutcome().values.fold(1) { a, b -> a * b }

    override fun toString(): String {
        return "Game(id=$id, outcomes=$outcomes)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Game

        if (id != other.id) return false
        if (outcomes != other.outcomes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + outcomes.hashCode()
        return result
    }
}