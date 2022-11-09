package week7._1025_제곱수찾기

import kotlin.math.sqrt

/**
 * 예외상황: 1 1 / 0 입력시에 어떻게 나오나??
 */

fun isSquare(number: Long): Boolean = sqrt(number.toDouble()) % 1 == 0.0

fun main() = with(System.`in`.bufferedReader()) {
    var res = -1L

    val (n, m) = readLine().split(" ").map { it.toInt() }

    val board = mutableListOf<String>()
    for (i in 0 until n) {
        val line = readLine()
        board.add(line)
    }

    fun concatString(firstRow: Int, firstCol: Int, secondRow: Int, secondCol: Int) {
        val rowStep = secondRow - firstRow
        val colStep = secondCol - firstCol

        var rowIdx = firstRow
        var colIdx = firstCol

        val stringBuilder = StringBuilder()
        while (rowIdx in 0 until n && colIdx in 0 until m) {
            stringBuilder.append(board[rowIdx][colIdx])
            val number = stringBuilder.toString().toLong()

            if (isSquare(number)) {
                res = maxOf(res, number)
            }

            rowIdx += rowStep
            colIdx += colStep
        }
    }

    for (startRow in 0 until n) {
        for (startCol in 0 until m) {
            for (secondRow in 0 until n) {
                for (secondCol in 0 until m) {
                    if ((secondRow == startRow && secondCol == startCol).not()) {
                        concatString(startRow, startCol, secondRow, secondCol)
                    } else {
                        if (isSquare(board[startRow][startCol].toString().toLong())){
                            res = maxOf(res, board[startRow][startCol].toString().toLong())
                        }
                    }
                }
            }
        }
    }

    println(res)
}