package week3._2805_절단기

import kotlin.math.max

fun main() {
    with(System.`in`.bufferedReader()) {
        var ans: Long = 0

        val (n, m) = readLine().split(" ").map { it.toInt() }
        val lst = readLine().split(" ").map { it.toInt() }

        var start = 1L
        var end = Int.MAX_VALUE.toLong()

        while (start <= end) {
            val mid: Long = (start + end) / 2
            var cnt = 0L
            lst.forEach {
                cnt += max(it - mid, 0)
            }
            if (cnt >= m) {
                ans = max(ans, mid)
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        println(ans.toInt())
    }
}