package week6._17070_파이프옮기기

import java.io.BufferedWriter
import java.io.OutputStreamWriter

/**
 * DP 사용
 */

enum class Direction(val number: Int) {
    Vertical(0), Horizontal(1), Diagonal(2)
}

data class Point(
    val row: Int,
    val col: Int,
    val direction: Direction
)

fun bfs(board: MutableList<MutableList<Int>>, size: Int): Int {
    var res = 0

    val queue = ArrayDeque<Point>()
    queue.add(Point(0, 1, Direction.Horizontal))

    val visited = List(size) { MutableList(size) { MutableList(3) { 0 } } }
    visited[0][1][Direction.Horizontal.number] = 1

    for (curRow in 0 until size){
        for (curCol in 0 until size){
            for (type in 0 until 3){
                val curVisitedNumber = visited[curRow][curCol][type]
                when (type) {
                    Direction.Horizontal.number -> {
                        if (curCol + 1 < size && board[curRow][curCol + 1] != 1) {
                            visited[curRow][curCol + 1][Direction.Horizontal.number] += curVisitedNumber
                        }

                        if (curRow + 1 < size && curCol + 1 < size) {
                            if (board[curRow + 1][curCol] + board[curRow][curCol + 1] + board[curRow + 1][curCol + 1] == 0) {
                                visited[curRow + 1][curCol + 1][Direction.Diagonal.number] += curVisitedNumber
                            }
                        }
                    }
                    Direction.Vertical.number -> {
                        if (curRow + 1 < size && board[curRow + 1][curCol] != 1) {
                            visited[curRow + 1][curCol][Direction.Vertical.number] += curVisitedNumber
                        }

                        if (curRow + 1 < size && curCol + 1 < size) {
                            if (board[curRow + 1][curCol] + board[curRow][curCol + 1] + board[curRow + 1][curCol + 1] == 0) {
                                visited[curRow + 1][curCol + 1][Direction.Diagonal.number] += curVisitedNumber
                            }
                        }
                    }
                    Direction.Diagonal.number -> {
                        if (curCol + 1 < size && board[curRow][curCol + 1] != 1) {
                            visited[curRow][curCol + 1][Direction.Horizontal.number] += curVisitedNumber
                        }
                        if (curRow + 1 < size && board[curRow + 1][curCol] != 1) {
                            visited[curRow + 1][curCol][Direction.Vertical.number] += curVisitedNumber
                        }

                        if (curRow + 1 < size && curCol + 1 < size) {
                            if (board[curRow + 1][curCol] + board[curRow][curCol + 1] + board[curRow + 1][curCol + 1] == 0) {
                                visited[curRow + 1][curCol + 1][Direction.Diagonal.number] += curVisitedNumber
                            }
                        }
                    }
                }
            }
        }
    }
    return visited[size - 1][size - 1].sum()
}

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val board = mutableListOf<MutableList<Int>>()
    for (i in 0 until n) {
        val line = readLine().trim().split(" ").map { it.toInt() }.toMutableList()
        board.add(line)
    }

    val bufferedWriter = BufferedWriter(OutputStreamWriter(System.out))
    bufferedWriter.write(bfs(board, n).toString())
    bufferedWriter.flush()
    bufferedWriter.close()
}