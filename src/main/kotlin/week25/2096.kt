package week25

private class Solution2096 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val result = mutableListOf<Int>()
        val n = readln().toInt()

        val dp = MutableList(3) { 0 }
        val prevDp = MutableList(3) { 0 }

        val board = mutableListOf<List<Int>>()
        for (i in 0 until n) {
            val line = readln().split(" ").map { it.toInt() }
            board.add(line)
        }

        for (i in 0 until 3) {
            dp[i] = board[0][i]
            prevDp[i] = board[0][i]
        }

        for (row in 1 until n) {
            for (col in 0 until 3) {
                if (col == 0) {
                    dp[col] = maxOf(prevDp[0], prevDp[1]) + board[row][col]
                } else if (col == 2) {
                    dp[col] = maxOf(prevDp[2], prevDp[1]) + board[row][col]
                } else {
                    dp[col] = maxOf(prevDp[0], prevDp[1], prevDp[2]) + board[row][col]
                }
            }

            for (i in 0 until 3) {
                prevDp[i] = dp[i]
            }
        }

        result.add(dp.maxOf { it })

        for (i in 0 until 3) {
            dp[i] = board[0][i]
            prevDp[i] = board[0][i]
        }

        for (row in 1 until n) {
            for (col in 0 until 3) {
                if (col == 0) {
                    dp[col] = minOf(prevDp[0], prevDp[1]) + board[row][col]
                } else if (col == 2) {
                    dp[col] = minOf(prevDp[2], prevDp[1]) + board[row][col]
                } else {
                    dp[col] = minOf(prevDp[0], prevDp[1], prevDp[2]) + board[row][col]
                }
            }

            for (i in 0 until 3) {
                prevDp[i] = dp[i]
            }
        }

        result.add(dp.minOf { it })

        println(result.joinToString(" "))
    }
}

fun main() {
    Solution2096().solution()
}
