package icu.trub.aoc.day11

import icu.trub.aoc.util.Point


data class Universe(val galaxyIdToCoordinates: Map<Int, Point>) {
    /**
     * @return [Point] for galaxy with the provided id
     */
    fun get(galaxyId: Int) = galaxyIdToCoordinates[galaxyId]!!

    /**
     * @return a new universe, with each empty line (having no galaxies) replaced by [factor] lines.
     * For example, with `factor = 2` an empty line would be turned into 2 empty lines.
     */
    fun expand(factor: Int = 2): Universe = Universe(buildMap {
        val knownCoordinates = galaxyIdToCoordinates.values
        val xExpansionCoordinates = (0..knownCoordinates.maxOf { it.x }).asSequence()
            .filter { x -> knownCoordinates.none { it.x == x } }
        val yExpansionCoordinates = (0..knownCoordinates.maxOf { it.y }).asSequence()
            .filter { y -> knownCoordinates.none { it.y == y } }

        putAll(galaxyIdToCoordinates)
        xExpansionCoordinates.sortedDescending().forEach { x ->
            filterValues { it.x > x }
                .mapValues { (_, point) -> point.right(factor - 1) }
                .let { putAll(it) }
        }
        yExpansionCoordinates.sortedDescending().forEach { y ->
            filterValues { it.y > y }
                .mapValues { (_, point) -> point.down(factor - 1) }
                .let { putAll(it) }
        }
    })

    private fun Point.right(amount: Int = 1) = Point(x + amount, y)
    private fun Point.down(amount: Int = 1) = Point(x, y + amount)

    /**
     * @return a map from [Pair] of galaxy ids to the length of the shortest path between them
     */
    fun findShortestDistancesBetweenGalaxies(): Map<Pair<Int, Int>, Int> = buildPairsOfGalaxyIds()
        .associateWith { get(it.first) distanceTo get(it.second) }

    private fun buildPairsOfGalaxyIds() = buildSet {
        val maxGalaxyId = galaxyIdToCoordinates.size
        (1..maxGalaxyId).forEach { i ->
            (i + 1..maxGalaxyId).forEach { j ->
                add(i to j)
            }
        }
    }

    companion object {
        fun parse(input: Sequence<String>): Universe = Universe(buildMap {
            var id = 0
            input.forEachIndexed { y, line ->
                line.forEachIndexed { x, char ->
                    if (char == '#') put(++id, Point(x, y))
                }
            }
        })
    }
}
