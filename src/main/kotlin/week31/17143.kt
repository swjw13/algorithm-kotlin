package week31

import java.lang.Exception

private class Solution17143 {

    fun solution() = with(System.`in`.bufferedReader()) {

        val (r, c, m) = readln().split(" ").map { it.toInt() }

        val rowTmp = r - 1
        val colTmp = c - 1

        fun getPosition(direction: Int, point: Int): Int {
            return if (direction == DIR_VERTICAL) {
                if (point % (2 * rowTmp) <= rowTmp) {
                    point % (2 * rowTmp)
                } else {
                    rowTmp - (point % rowTmp)
                }
            } else {
                if (point % (2 * colTmp) <= colTmp) {
                    point % (2 * colTmp)
                } else {
                    colTmp - (point % colTmp)
                }
            }
        }

        var board = List(r) { MutableList(c) { listOf<Int>() } }
        for (i in 0 until m) {
            val (tmpR, tmpC, curS, curD, curZ) = readln().split(" ").map { it.toInt() }

            var curR = tmpR - 1
            var curC = tmpC - 1

            val direction = when (curD) {
                1 -> {
                    curR += 2 * (rowTmp - curR)
                    DIR_VERTICAL
                }

                2 -> { DIR_VERTICAL }

                3 -> { DIR_HORIZONTAL }

                4 -> {
                    curC += 2 * (colTmp - curC)
                    DIR_HORIZONTAL
                }

                else -> throw Exception()
            }

            val rowPosition = getPosition(DIR_VERTICAL, curR)
            val colPosition = getPosition(DIR_HORIZONTAL, curC)

            if (board[rowPosition][colPosition].isEmpty()) {
                board[rowPosition][colPosition] = listOf(curR, curC, direction, curS, curZ)
            } else {
                if (board[rowPosition][colPosition][4] < curZ) {
                    board[rowPosition][colPosition] = listOf(curR, curC, direction, curS, curZ)
                }
            }
        }

        var res = 0

        for (fisherMan in 0 until c) {
            for (row in 0 until r) {
                if (board[row][fisherMan].isNotEmpty()) {
                    res += board[row][fisherMan][4]
                    board[row][fisherMan] = listOf()
                    break
                }
            }

            val newBoard = List(r) { MutableList(c) { listOf<Int>() } }
            for (row in 0 until r) {
                for (col in 0 until c) {
                    if (board[row][col].isNotEmpty()) {
                        val direction = board[row][col][2]
                        val rowPosition: Int
                        val colPosition: Int
                        val newRow: Int
                        val newCol: Int

                        if (direction == DIR_VERTICAL) {
                            newRow = board[row][col][0] + board[row][col][3]

                            rowPosition = getPosition(DIR_VERTICAL, newRow)
                            newCol = board[row][col][1]
                            colPosition = getPosition(DIR_HORIZONTAL, newCol)

                        } else {
                            newCol = board[row][col][1] + board[row][col][3]

                            colPosition = getPosition(DIR_HORIZONTAL, newCol)
                            newRow = board[row][col][0]
                            rowPosition = getPosition(DIR_VERTICAL, newRow)

                        }

                        if (newBoard[rowPosition][colPosition].isEmpty()) {
                            newBoard[rowPosition][colPosition] = listOf(
                                newRow, newCol, direction, board[row][col][3], board[row][col][4]
                            )
                        } else {
                            if (newBoard[rowPosition][colPosition][4] < board[row][col][4]) {
                                newBoard[rowPosition][colPosition] = listOf(
                                    newRow, newCol, direction, board[row][col][3], board[row][col][4]
                                )
                            }
                        }
                    }
                }
            }

            board = newBoard
        }

        println(res)
    }

    companion object {
        const val DIR_VERTICAL = -1
        const val DIR_HORIZONTAL = 1
    }
}

fun main() {
    Solution17143().solution()
}