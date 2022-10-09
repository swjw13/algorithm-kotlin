package week2._2467_용액

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    var minTotalValue = Int.MAX_VALUE
    var minValue = -1
    var maxValue = -1

    val n = reader.readLine().toInt()
    val numberList = reader.readLine().split(" ").map { it.toInt() }.sorted()

    var startIdx = 0
    var endIdx = n - 1

    while (startIdx < endIdx) {
        val curTotal = numberList[startIdx] + numberList[endIdx]

        if (abs(curTotal) <= minTotalValue) {
            minTotalValue = abs(curTotal)
            minValue = numberList[startIdx]
            maxValue = numberList[endIdx]
        }

        if (curTotal < 0) startIdx += 1
        else endIdx -= 1
    }

    println("$minValue $maxValue\n")
}