package week39

import kotlin.math.max

private object Solution20207 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        val plusList = MutableList(367) { 0 }
        val minusList = MutableList(367) { 0 }
        var curCnt = 0

        for (i in 0 until n){
            val (start, end) = readln().split(" ").map { it.toInt() }
            plusList[start] += 1
            minusList[end + 1] += 1
        }

        var startDay = 0
        var maxTime = 0
        var result = 0

        for (day in 1 .. 366){
            val prevCnt = curCnt
            curCnt += (plusList[day] - minusList[day])

            when {
                prevCnt == 0 && curCnt == 0 -> {
                    continue
                }
                prevCnt != 0 && curCnt == 0 -> {
                    result += (day - startDay) * maxTime
                    maxTime = 0
                    startDay = 0
                }
                prevCnt == 0 && curCnt != 0 -> {
                    startDay = day
                    maxTime = curCnt
                }
                else -> {
                    maxTime = max(maxTime, curCnt)
                }
            }
        }

        println(result)
    }
}

fun main() {
    Solution20207.solution()
}