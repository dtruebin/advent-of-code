package icu.trub.aoc.util.string

/**
 * @return a string having chars at the given positions replaced with the provided char
 */
fun String.replaceCharAtIndices(indices: Collection<Int>, replacement: Char) = foldIndexed(this) { i, acc, _ ->
    if (i in indices) acc.replaceRange(i..i, replacement.toString()) else acc
}