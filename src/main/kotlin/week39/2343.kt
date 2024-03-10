package week39

import kotlin.math.min

private object Solution2343 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val lst = readln().split(" ").map { it.toInt() }

        var high = 100_000 * 10_000L
        var low = 1L
        var result = high

        loop@ while (high >= low) {
            val mid: Long = (high + low) / 2
            var curGroupSize = 0L
            var groupCnt = 0L

            for (size in lst) {
                if (size > mid) {
                    low = mid + 1
                    continue@loop
                }
                if (curGroupSize + size > mid) {
                    curGroupSize = 0L
                    groupCnt += 1L
                }

                curGroupSize += size
            }
            if (curGroupSize != 0L) groupCnt += 1L

            if (groupCnt <= m) {
                result = min(result, mid)
                high = mid - 1
            } else {
                low = mid + 1
            }
        }

        println(result)
    }
}

fun main() {
    Solution2343.solution()
}