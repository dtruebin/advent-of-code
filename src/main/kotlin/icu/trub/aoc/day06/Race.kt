package icu.trub.aoc.day06

data class Race(val allowedTime: Long, val recordDistance: Long) {
    fun getOutcome(buttonHoldTime: Long): Long {
        val speed = when (buttonHoldTime < allowedTime) {
            true -> buttonHoldTime
            false -> 0
        }
        val remainingTime = allowedTime - buttonHoldTime
        return remainingTime * speed
    }

    fun getCountOfPossibleWins(): Int = (0..allowedTime).map(::getOutcome).count { it > recordDistance }
}