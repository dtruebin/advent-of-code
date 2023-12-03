fun main() {
    val inputFile = object {}.javaClass.getResourceAsStream("day01.txt") ?: return
    val sum = inputFile.bufferedReader().lines()
        .map(::getDoubleDigitFromFirstAndLast)
        .toList()
        .sum()
    println(sum)
}

internal fun getDoubleDigitFromFirstAndLast(line: String): Int {
    return "${line.first(Char::isDigit)}${line.last(Char::isDigit)}".toInt()
}