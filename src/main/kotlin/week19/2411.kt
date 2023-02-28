package week19

private class Solution2411 {

    private data class Point(
        val row: Int,
        val col: Int
    )

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, a, b) = readln().split(" ").map { it.toInt() }
        val board = List(n) { MutableList(m) { 0 } }
        val itemList = mutableListOf<Point>()

        for (i in 0 until a) {
            val (itemRow, itemCol) = readln().split(" ").map { it.toInt() }
            itemList.add(Point(itemRow - 1, itemCol - 1))
        }
        itemList.sortWith(compareBy({ it.row }, { it.col }))
        itemList.add(Point(n - 1, m - 1))

        for (i in 0 until b) {
            val (rockRow, rockCol) = readln().split(" ").map { it.toInt() }
            board[rockRow - 1][rockCol - 1] = 2
        }

        var curPoint = Point(0, 0)
        val dp = List(n) { MutableList(m) { 0 } }
        dp[0][0] = 1

        for (item in itemList) {
            for (row in curPoint.row..item.row) {
                for (col in curPoint.col..item.col) {
                    if (board[row][col] == 2) dp[row][col] = 0
                    else if ((row == curPoint.row && col == curPoint.col).not()) {
                        var count = 0
                        if (row - 1 in 0 until n) count += dp[row - 1][col]
                        if (col - 1 in 0 until m) count += dp[row][col - 1]

                        dp[row][col] += count
                    }
                }
            }
            curPoint = item
        }
        println(dp[n - 1][m - 1])
    }
}

fun main() {
    Solution2411().solution()
}