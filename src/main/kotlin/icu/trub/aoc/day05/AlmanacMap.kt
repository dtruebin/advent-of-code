package icu.trub.aoc.day05

class AlmanacMap(private val rangeDescriptors: List<RangeDescriptor>) {
    fun get(key: Long): Long = try {
        rangeDescriptors.sortedBy { it.srcStart }
            .filter { key in it.srcRange }
            .map { key - it.srcStart + it.dstStart }
            .first()
    } catch (e: NoSuchElementException) {
        key
    }

    class RangeDescriptor(val srcStart: Long, val dstStart: Long, private val rangeLength: Long) {
        val srcRange: LongRange
            get() {
                return srcStart..<srcStart + rangeLength
            }

        companion object {
            fun parse(line: String): RangeDescriptor {
                val (dstStart, srcStart, rangeLength) = line.split(' ').map(String::toLong)
                return RangeDescriptor(srcStart, dstStart, rangeLength)
            }
        }
    }
}