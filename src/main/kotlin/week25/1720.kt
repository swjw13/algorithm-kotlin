package week25

private class Solution1720 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readln().toInt()
        if (n == 1) println(1)
        else {
            val dp = MutableList(n + 1) { 0 }
            dp[0] = 1
            dp[1] = 1

            for (i in 2..n) {
                dp[i] = dp[i - 1] + dp[i - 2] * 2
            }

            if (n % 2 == 1) {
                println((dp[n] + dp[(n - 1) / 2]) / 2)
            } else {
                println((dp[n] + dp[n / 2] + dp[n / 2 - 1] * 2) / 2)
            }
        }
    }
}

fun main() {
    Solution1720().solution()
}