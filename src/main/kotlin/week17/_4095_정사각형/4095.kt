package week17._4095_정사각형

object Solution4095 {
    private lateinit var board: MutableList<List<Int>>
    fun solution() = with(System.`in`.bufferedReader()) {
        while (true) {
            var res = 0
            val (n, m) = readln().split(" ").map { it.toInt() }
            if (n == 0 && m == 0) break

            board = mutableListOf()
            val dp = List(n) { MutableList(m) { 0 } }

            for (i in 0 until n) {
                board.add(readln().split(" ").map { it.toInt() })
            }
            board.sortedWith(compareBy(   ))

            for (row in 0 until n) {
                for (col in 0 until m) {
                    if (row == 0 || col == 0) {
                        dp[row][col] = board[row][col]
                        res = maxOf(res, dp[row][col])
                    } else {
                        if (board[row][col] == 0) {
                            dp[row][col] = 0
                        } else {
                            dp[row][col] = minOf(dp[row - 1][col], dp[row][col - 1], dp[row - 1][col - 1]) + 1
                            res = maxOf(res, dp[row][col])
                        }
                    }
                }
            }

            println(res)
        }
    }
}

fun main() {
    Solution4095.solution()
}
