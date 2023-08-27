package week28

import kotlin.math.abs

private class Solution5710 {

    fun getTotalUsage(price: Long): Long {
        return if (price <= 200) (price / 2)
        else if (price <= 29900) (100 + (price - 200) / 3)
        else if (price <= 4979900) (10000 + (price - 29900) / 5)
        else (1000000 + (price - 4979900) / 7)
    }

    fun getPrice(usage: Long): Long {
        return if (usage <= 100) usage * 2
        else if (usage <= 10000) 200 + (usage - 100) * 3
        else if (usage <= 1000000) 200 + 9900 * 3 + (usage - 10000) * 5
        else 200 + 9900 * 3 + 990000 * 5 + (usage - 1000000) * 7
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        while (true) {
            val (a, b) = readln().split(" ").map { it.toLong() }
            if (a == 0L && b == 0L) break

            val totalUsage = getTotalUsage(a)

            var start: Long = 0
            var end = totalUsage
            while (start <= end){
                val mid: Long = (start + end) / 2

                val resultOne = getPrice(mid)
                val resultTwo = getPrice(totalUsage - mid)

                val result = abs(resultOne - resultTwo)
                if (result == b){
                    println(minOf(resultOne, resultTwo))
                    break
                } else if (result < b){
                    if (mid <= totalUsage / 2){
                        end = mid - 1
                    } else {
                        start = mid + 1
                    }
                } else {
                    if (mid > totalUsage / 2){
                        end = mid - 1
                    } else {
                        start = mid + 1
                    }
                }
            }

        }
    }
}

fun main() {
    Solution5710().solution()
}