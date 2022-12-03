package week9

import kotlin.math.absoluteValue

fun main() = with(System.`in`.bufferedReader()) {
    var minTotal = Long.MAX_VALUE
    var minValues = Pair(-1L, -1L)

    val n = readLine().toInt()
    val lst = readLine().split(" ").map { it.toLong() }
    val lstSorted = lst.sorted()

    var startIdx = 0
    var endIdx = lstSorted.size - 1

    while (startIdx < endIdx) {
        val sumValue = lstSorted[startIdx] + lstSorted[endIdx]

        if (sumValue.absoluteValue < minTotal) {
            minTotal = sumValue.absoluteValue
            minValues =
                Pair(minOf(lstSorted[startIdx], lstSorted[endIdx]), maxOf(lstSorted[startIdx], lstSorted[endIdx]))
        }

        if (sumValue < 0) startIdx += 1
        else endIdx -= 1
    }

    println("${minValues.first} ${minValues.second}")
}