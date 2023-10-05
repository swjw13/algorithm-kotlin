package week28

private class Solution1865 {
    fun solution() {
        val t = readln().toInt()
        case@ for (c in 0 until t) {
            val (n, m, w) = readln().split(" ").map { it.toInt() }

            val board = List(n + 1) { MutableList(n + 1) { 0 } }
            for (i in 0 until m) {
                val (e1, e2, d) = readln().split(" ").map { it.toInt() }
                if (board[e1][e2] == 0) {
                    board[e1][e2] = d
                    board[e2][e1] = d
                } else {
                    board[e1][e2] = minOf(board[e1][e2], d)
                    board[e2][e1] = minOf(board[e2][e1], d)
                }
            }

            for (i in 0 until w) {
                val (e1, e2, d) = readln().split(" ").map { it.toInt() }
                board[e1][e2] = -d
            }

            val lines = mutableListOf<List<Int>>()
            for (row in 1..n) {
                for (col in 1..n) {
                    if (board[row][col] != 0) lines.add(listOf(row, col, board[row][col]))
                }
            }

            val dist = MutableList(n + 1) { 500 * 10000 + 5 }
            dist[1] = 0

            for (i in 0 until n) {
                for ((row, col, d) in lines) {
                    if (dist[row] + d < dist[col]) dist[col] = dist[row] + d
                }
            }

            for ((row, col, d) in lines) {
                if (dist[row] + d < dist[col]) {
                    println("YES")
                    continue@case
                }
            }

            println("NO")
        }
    }
}

fun main() {
    Solution1865().solution()
}