package icu.trub.aoc.day08

class Network(val nodes: Map<String, Pair<String, String>>) {
    fun navigate(startingNode: String, targetNode: String, instructions: String): List<String> = buildList {
        val i = ArrayDeque(instructions.toList())
        var currentNode = startingNode
        while (currentNode != targetNode) {
            when (i.rotate()) {
                'L' -> currentNode = nodes[currentNode]!!.first
                'R' -> currentNode = nodes[currentNode]!!.second
            }
            add(currentNode)
        }
    }

    /**
     * Polls the queue and adds the same element back into it.
     */
    private fun <T> ArrayDeque<T>.rotate(): T {
        val first = removeFirst()
        addLast(first)
        return first
    }

    companion object {
        fun parse(lines: List<String>): Network = Network(
            lines.filter { it.isNotBlank() }
                .map { it.trim() }
                .associate {
                    val (src, dst) = it.split(" = ")
                    val (left, right) = dst.trim('(', ')').split(", ")
                    src to (left to right)
                })
    }
}