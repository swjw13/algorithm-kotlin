package week32

import kotlin.math.sqrt

/**
 * BackTracking 사용
 * 만약 해당 숫자가 소수라면 다음 순서 진행, 아니면 끝
 * 최종 길이가 되었을 때 print
 */
private class Solution2023 {
    var n: Int = 0

    fun isPrime(n: Int): Boolean {
        for (i in 2..sqrt(n.toFloat()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }

    fun backTracking(prev: String) {
        if (prev.length == n) {
            println(prev)
            return
        }

        for (i in 0..9) {
            val tmp = prev + i

            if (isPrime(tmp.toInt())) {
                backTracking(tmp)
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        n = readln().toInt()

        backTracking("2")
        backTracking("3")
        backTracking("5")
        backTracking("7")
    }
}

fun main() {
    Solution2023().solution()
}