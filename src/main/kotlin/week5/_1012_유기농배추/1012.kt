package week5._1012_유기농배추

import java.util.LinkedList
import java.util.Queue

fun main() {

    data class Point(
        val row: Int,
        val col: Int
    )

    val dxdy = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
    fun bfs(row: Int, col: Int, board: List<MutableList<Int>>) {
        val queue = ArrayDeque<Point>()
        queue.add(Point(row, col))
        board[row][col] = 0

        while (queue.isNotEmpty()) {
            val curPoint = queue.removeFirst()
            for (movement in dxdy) {
                val newRow = curPoint.row + movement.first
                val newCol = curPoint.col + movement.second

                if (newRow in board.indices && newCol in board[0].indices && board[newRow][newCol] == 1) {
                    board[newRow][newCol] = 0
                    queue.add(Point(newRow, newCol))
                }
            }
        }
    }

    with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        for (test in 0 until t) {
            var res = 0
            val (m, n, k) = readLine().trim().split(" ").map { it.toInt() }

            val ground = List(n) { MutableList(m) { 0 } }

            for (i in 0 until k) {
                val (x, y) = readLine().trim().split(" ").map { it.toInt() }
                ground[y][x] = 1
            }

            for (row in 0 until n) {
                for (col in 0 until m) {
                    if (ground[row][col] == 1) {
                        res += 1
                        bfs(row, col, ground)
                    }
                }
            }

            println(res)
        }
    }
}