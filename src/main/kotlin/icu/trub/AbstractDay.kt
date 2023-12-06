package icu.trub

abstract class AbstractDay(val inputFileName: String) {
    /**
     * @return list of solutions, one per day part.
     */
    abstract fun solve(): List<Int>
}