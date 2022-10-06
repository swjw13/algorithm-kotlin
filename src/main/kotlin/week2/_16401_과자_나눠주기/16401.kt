package week2._16401_과자_나눠주기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections.max

fun main() {
    var ans = 0
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (m, n) = reader.readLine().split(" ").map { it.toInt() }
    val lst = reader.readLine().split(" ").map { it.toInt() }

    var min = 0
    var max = max(lst)

    while (min <= max) {
        val mid = (min + max) / 2
        if (mid == 0) break
        var cnt = 0
        for (i in lst) cnt += (i / mid)

        if (cnt >= m) {
            ans = kotlin.math.max(ans, mid)
            min = mid + 1
        } else {
            max = mid - 1
        }
    }

    println(ans)
}