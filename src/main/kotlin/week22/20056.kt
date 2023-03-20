package week22

import kotlin.math.abs

private class Solution20056 {

    private val dxdy =
        listOf(Pair(-1, 0), Pair(-1, 1), Pair(0, 1), Pair(1, 1), Pair(1, 0), Pair(1, -1), Pair(0, -1), Pair(-1, -1))

    data class BoardElement(
        val m: Int,
        val s: Int,
        val d: Int
    )

    private fun nextPoint(newPoint: Int, rowSize: Int): Int{
        return if (newPoint >= 0) newPoint % rowSize
        else {
            val abs = abs(newPoint)
            if (abs == rowSize) 0
            else (newPoint + (abs / rowSize + 1) * rowSize) % rowSize
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = readln().split(" ").map { it.toInt() }
        var board = List(n) { MutableList(n) { mutableListOf<BoardElement>() } }
        for (i in 0 until m) {
            val (ri, ci, mi, si, di) = readln().split(" ").map { it.toInt() }
            board[ri - 1][ci - 1].add(BoardElement(mi, si, di))
        }

        for (i in 0 until k) {
            val newBoard = List(n) { MutableList(n) { mutableListOf<BoardElement>() } }
            for (row in 0 until n) {
                for (col in 0 until n) {
                    for (element in board[row][col]) {
                        val movement = dxdy[element.d]

                        val newRow = nextPoint(row + movement.first * element.s, n)
                        val newCol = nextPoint(col + movement.second * element.s, n)

                        newBoard[newRow][newCol].add(element)
                    }
                }
            }

            for (row in 0 until n) {
                for (col in 0 until n) {
                    val size = newBoard[row][col].size
                    if (size >= 2) {
                        var totalM = 0
                        var totalS = 0
                        var isEven = 0
                        for (element in newBoard[row][col]) {
                            totalM += element.m
                            totalS += element.s
                            if (element.d % 2 == 0) isEven += 1
                        }
                        totalM /= 5
                        totalS /= size

                        newBoard[row][col].clear()
                        if (totalM != 0) {
                            val directionList = if (isEven == 0 || isEven == size) {
                                listOf(0, 2, 4, 6)
                            } else {
                                listOf(1, 3, 5, 7)
                            }

                            directionList.forEach {
                                newBoard[row][col].add(BoardElement(totalM, totalS, it))
                            }
                        }
                    }
                }
            }

            board = newBoard
        }

        println(board.sumOf { it.sumOf { it.sumOf { it.m } } })
    }
}

fun main() {
    Solution20056().solution()
}