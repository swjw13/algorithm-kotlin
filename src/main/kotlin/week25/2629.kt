package week25

import java.io.StreamTokenizer

private class Solution2629 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val ballSize = readln().toInt()
        val balls = readln().split(" ").map { it.toInt() }
        val tests = readln().split(" ").map { it.toInt() }
        val maxSize = balls.sum()

        val dp = List(ballSize + 1) { MutableList(maxSize + 1) { false } }
        dp[0][maxSize] = true

        for (row in 0 until ballSize) {
            for (col in 1..maxSize) {
                if (dp[row][col]) {
                    dp[row + 1][col] = true
                    if (col - balls[row] >= 1) {
                        dp[row + 1][col - balls[row]] = true
                    }
                    if (col - balls[row] * 2 >= 1) {
                        dp[row + 1][col - balls[row] * 2] = true
                    }
                }
            }
        }

        val result = mutableListOf<String>()
        for (i in tests) {
            if (i <= maxSize && dp.last()[i]) result.add("Y")
            else result.add("N")
        }
        println(result.joinToString(" "))
    }
}

fun main() {
    Solution2629().solution()
}

