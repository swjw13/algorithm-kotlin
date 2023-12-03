package week34

private class Solution6087 {

    data class QueueElement(
        val row: Int,
        val col: Int,
        val prevLength: Int,
        val prevDirection: Int
    )

    operator fun String.times(n: Int): String {
        var res = ""
        for (i in 0 until n) res += this
        return res
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (w, h) = readln().split(" ").map(String::toInt)
        val board = mutableListOf<String>()
        val lightPos = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until h) {
            val line = readln().trim()
            if ("C" in line) {
                lightPos.add(Pair(i, line.indexOf("C")))
            }
            board.add(line)
        }
        val visited = List(h) { MutableList(w) { 201 } }
        val (startRow, startCol) = lightPos[0]
        val queue = ArrayDeque<QueueElement>().apply {
            visited[startRow][startCol] = 0
            add(QueueElement(startRow, startCol, 0, -1))
        }

        val (lastRow, lastCol) = lightPos[1]
        val answers = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            val (curRow, curCol, curPrevLength, curDirection) = queue.removeFirst()
            if (curRow == lastRow && curCol == lastCol) {
                answers.add(curPrevLength)
            } else {
                if (curPrevLength > visited[curRow][curCol]) continue

                if (curRow - 1 >= 0 && board[curRow - 1][curCol] != '*' && visited[curRow - 1][curCol] >= curPrevLength + 1) {
                    visited[curRow][curCol] = if (curDirection == 0) {
                        queue.add(QueueElement(curRow - 1, curCol, curPrevLength, 0))
                        curPrevLength
                    } else {
                        queue.add(QueueElement(curRow - 1, curCol, curPrevLength + 1, 0))
                        curPrevLength + 1
                    }
                }

                if (curCol - 1 >= 0 && board[curRow][curCol - 1] != '*' && visited[curRow][curCol - 1] >= curPrevLength + 1) {
                    visited[curRow][curCol] = if (curDirection == 3) {
                        queue.add(QueueElement(curRow, curCol - 1, curPrevLength, 3))
                        curPrevLength
                    } else {
                        queue.add(QueueElement(curRow, curCol - 1, curPrevLength + 1, 3))
                        curPrevLength + 1
                    }
                }

                if (curRow + 1 < h && board[curRow + 1][curCol] != '*' && visited[curRow + 1][curCol] >= curPrevLength + 1) {
                    visited[curRow][curCol] = if (curDirection == 2) {
                        queue.add(QueueElement(curRow + 1, curCol, curPrevLength, 2))
                        curPrevLength
                    } else {
                        queue.add(QueueElement(curRow + 1, curCol, curPrevLength + 1, 2))
                        curPrevLength + 1
                    }
                }

                if (curCol + 1 < w && board[curRow][curCol + 1] != '*' && visited[curRow][curCol + 1] >= curPrevLength + 1) {
                    visited[curRow][curCol] = if (curDirection == 1) {
                        queue.add(QueueElement(curRow, curCol + 1, curPrevLength, 1))
                        curPrevLength
                    } else {
                        queue.add(QueueElement(curRow, curCol + 1, curPrevLength + 1, 1))
                        curPrevLength + 1
                    }
                }
            }
        }
        answers.sortBy { it }
        println(answers[0] - 1)
    }
}

fun main() {
    Solution6087().solution()
}