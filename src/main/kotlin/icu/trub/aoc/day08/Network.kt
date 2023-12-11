package icu.trub.aoc.day08

import icu.trub.aoc.AocUtil

class Network(val nodes: Map<String, Pair<String, String>>) {
    fun navigate(startingNodeSuffix: String, targetNodeSuffix: String, instructions: String): Long =
        nodes.keys.filter { it.endsWith(startingNodeSuffix) }
            .map { startingNode ->
                val i = ArrayDeque(instructions.toList())
                var currentNode = startingNode
                var hops = 0L
                while (!currentNode.endsWith(targetNodeSuffix)) {
                    hops++
                    when (i.rotate()) {
                        'L' -> currentNode = nodes[currentNode]!!.first
                        'R' -> currentNode = nodes[currentNode]!!.second
                    }
                }
                hops
            }.let { AocUtil.findLCM(it) }

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