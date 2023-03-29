package week23

private class Solution17144 {

    data class Point(
        val row: Int,
        val col: Int,
        val weight: Int
    )

    private val airPurifier = mutableListOf<Point>()
    private val dxdy = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, 1), Pair(0, -1))

    private fun isOnUpperPurifier(curRow: Int, curCol: Int, m: Int): Boolean {
        return if (curRow == 0 || curRow == airPurifier[0].row) true
        else (curCol == 0 || curCol == m - 1) && (curRow in 0 until airPurifier[0].row)
    }

    private fun isOnDownPurifier(curRow: Int, curCol: Int, n: Int, m: Int): Boolean {
        return if (curRow == airPurifier[1].row || curRow == n - 1) return true
        else (curCol == 0 || curCol == m - 1) && (curRow in airPurifier[1].row until n)
    }

    private fun isPurifierPoint(curRow: Int, curCol: Int): Boolean {
        return (curRow == airPurifier[0].row && curCol == airPurifier[0].col) || (curRow == airPurifier[1].row && curCol == airPurifier[1].col)
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, t) = readln().split(" ").map { it.toInt() }
        var board = mutableListOf<MutableList<Int>>()
        for (idx in 0 until n) {
            val line = readln().split(" ").map { it.toInt() }
            if (-1 in line) airPurifier.add(Point(idx, line.indexOf(-1), -1))
            board.add(line.toMutableList())
        }

        repeat(t) {
            val tmpBoard = MutableList(n) { MutableList(m) { 0 } }

            for (row in 0 until n) {
                for (col in 0 until m) {
                    if (board[row][col] > 0) {
                        val weight = board[row][col]
                        var cnt = 0

                        for (movement in dxdy) {
                            val newRow = row + movement.first
                            val newCol = col + movement.second
                            val newWeight = weight / 5
                            if (newRow in 0 until n && newCol in 0 until m && isPurifierPoint(newRow, newCol).not()) {
                                cnt += 1
                                tmpBoard[newRow][newCol] += newWeight
                            }
                        }

                        tmpBoard[row][col] += (weight - (weight / 5) * cnt)
                    }
                }
            }

            val newBoard = MutableList(n) { MutableList(m) { 0 } }
            for (row in 0 until n) {
                for (col in 0 until m) {
                    if (tmpBoard[row][col] > 0) {
                        var newRow = row
                        var newCol = col
                        if (isOnUpperPurifier(row, col, m)) {
                            when (row) {
                                0 -> {
                                    when (col) {
                                        0 -> newRow += 1
                                        else -> newCol -= 1
                                    }
                                }

                                airPurifier[0].row -> {
                                    when (col) {
                                        m - 1 -> newRow -= 1
                                        else -> newCol += 1
                                    }
                                }

                                else -> {
                                    when (col) {
                                        0 -> newRow += 1
                                        else -> newRow -= 1
                                    }
                                }
                            }
                        } else if (isOnDownPurifier(row, col, n, m)) {
                            when (row) {
                                airPurifier[1].row -> {
                                    when (col) {
                                        m - 1 -> newRow += 1
                                        else -> newCol += 1
                                    }
                                }

                                n - 1 -> {
                                    when (col) {
                                        0 -> newRow -= 1
                                        else -> newCol -= 1
                                    }
                                }

                                else -> {
                                    when (col) {
                                        0 -> newRow -= 1
                                        else -> newRow += 1
                                    }
                                }
                            }
                        }
                        if (isPurifierPoint(newRow, newCol).not()) newBoard[newRow][newCol] += tmpBoard[row][col]
                    }
                }
            }
            board = newBoard
        }

        println(board.sumOf { it.sum() })
    }
}

fun main() {
    Solution17144().solution()
}