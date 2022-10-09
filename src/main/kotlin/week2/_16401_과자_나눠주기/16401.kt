package week2._16401_과자_나눠주기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections.max

/**
 * lst.macOf()
 * 매개 변수 탐색
 * with(BufferedReader 사용하기)
 */

fun main() {
    var ans = 0
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (m, n) = reader.readLine().split(" ").map { it.toInt() }
    val lst = reader.readLine().split(" ").map { it.toInt() }

    var min = 1
    var max = max(lst)

    while (min <= max) {
        val mid = (min + max) / 2

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