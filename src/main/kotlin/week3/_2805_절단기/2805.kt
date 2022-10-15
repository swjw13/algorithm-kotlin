package week3._2805_절단기

import kotlin.math.max

tailrec fun factorial(n: Int): Int {
    return factorial(n - 2)
}

fun main() {
    with(System.`in`.bufferedReader()) {
        var ans = 0

        val (n, m) = readLine().split(" ").map { it.toInt() }
        val lst = readLine().split(" ").map { it.toInt() }

        var start = 1
        var end = Int.MAX_VALUE

        while (start <= end) {
            val mid = (start + end) / 2
            var cnt = 0
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
        println(ans)
    }
}