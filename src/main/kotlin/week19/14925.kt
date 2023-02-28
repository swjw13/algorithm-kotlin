package week19

private class Solution14925 {
    fun solution() = with(System.`in`.bufferedReader()) {
        var answer = 0

        val (n, m) = readln().split(" ").map { it.toInt() }
        val board = mutableListOf<List<Int>>()
        for (i in 0 until n) {
            val line = readln().split(" ").map { it.toInt() }
            board.add(line)
        }

        val dp = List(n) { MutableList(m) { 0 } }
        for (row in 0 until n) {
            for (col in 0 until m) {
                if (board[row][col] == 1 || board[row][col] == 2) dp[row][col] = 0
                else if (row == 0 || col == 0) {
                    dp[row][col] = 1
                    answer = maxOf(answer, 1)
                }
                else {
                    var tmp = Int.MAX_VALUE
                    tmp = when (board[row][col - 1]){
                        0 -> minOf(tmp, dp[row][col - 1])
                        else -> 0
                    }
                    tmp = when (board[row - 1][col]){
                        0 -> minOf(tmp, dp[row - 1][col])
                        else -> 0
                    }
                    tmp = when (board[row - 1][col - 1]){
                        0 -> minOf(tmp, dp[row - 1][col - 1])
                        else -> 0
                    }

                    dp[row][col] = tmp + 1
                    answer = maxOf(answer, tmp + 1)
                }
            }
        }

        println(answer)
    }
}

fun main() {
    Solution14925().solution()
}