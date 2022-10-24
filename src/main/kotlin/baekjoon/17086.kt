package baekjoon

import java.util.LinkedList
import java.util.Queue

fun main() {

    var answer = 0

    data class Point(
        val row: Int,
        val col: Int,
        val count: Int
    )

    val movement = mutableListOf<Pair<Int, Int>>()
    for (dx in -1..1) {
        for (dy in -1..1) {
            movement.add(Pair(dx, dy))
        }
    }
    movement.remove(Pair(0, 0))

    fun bfs(row: Int, col: Int, board: List<List<Int>>): Int {
        val queue: Queue<Point> = LinkedList()
        queue.add(Point(row, col, 0))

        val visited = List(board.size) { MutableList(board[0].size) { false } }
        visited[row][col] = true

        while (queue.isNotEmpty()) {
            val curPoint = queue.poll()

            if (board[curPoint.row][curPoint.col] == 1) return curPoint.count

            for (dxdy in movement) {
                val newX = curPoint.row + dxdy.first
                val newY = curPoint.col + dxdy.second
                val newCount = curPoint.count + 1
                if (newX in board.indices && newY in board[0].indices && !visited[newX][newY]) {
                    visited[newX][newY] = true
                    queue.add(Point(newX, newY, newCount))
                }
            }
        }
        throw java.lang.Exception("잘못된 결과입니다")
    }

    with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val map = List(n) { readLine().split(" ").map { it.toInt() } }

        for (row in 0 until n){
            for (col in 0 until m){
                answer = maxOf(answer, bfs(row, col, map))
            }
        }

        println(answer)

    }
}