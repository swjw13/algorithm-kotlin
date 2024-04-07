package week41

import java.util.PriorityQueue

private object Solution16509 {
    fun solution() = with(System.`in`.bufferedReader()) {
        val (r1, c1) = readLine().split(" ").map { it.toInt() }
        val (r2, c2) = readLine().split(" ").map { it.toInt() }

        val visited = List(10) { MutableList(9) { false } }
        visited[r1][c1] = true

        val queue = ArrayDeque<Triple<Int, Int, Int>>().apply {
            add(Triple(r1, c1, 0))
        }

        while (queue.isNotEmpty()){
            val (curRow, curCol, curCnt) = queue.removeFirst()
            if (curRow == r2 && curCol == c2) {
                println(curCnt)
                return@with
            }

            var newRow = curRow
            var newCol = curCol

            newRow = curRow + 1
            if (isIn(newRow + 1, newCol + 1) && isIn(newRow + 2, newCol + 2)) {
                if ((r2 == newRow + 1 && c2 == newCol + 1).not() && visited[newRow + 2][newCol + 2].not()) {
                    visited[newRow + 2][newCol + 2] = true
                    queue.add(Triple(newRow + 2, newCol + 2, curCnt + 1))
                }
            }

            if (isIn(newRow + 1, newCol - 1) && isIn(newRow + 2, newCol - 2)) {
                if ((r2 == newRow + 1 && c2 == newCol - 1).not() && visited[newRow + 2][newCol - 2].not()) {
                    visited[newRow + 2][newCol - 2] = true
                    queue.add(Triple(newRow + 2, newCol - 2, curCnt + 1))
                }
            }

            newRow = curRow - 1
            if (isIn(newRow - 1, newCol + 1) && isIn(newRow - 2, newCol + 2)) {
                if ((r2 == newRow - 1 && c2 == newCol + 1).not() && visited[newRow - 2][newCol + 2].not()) {
                    visited[newRow - 2][newCol + 2] = true
                    queue.add(Triple(newRow - 2, newCol + 2, curCnt + 1))
                }
            }

            if (isIn(newRow - 1, newCol - 1) && isIn(newRow - 2, newCol - 2)) {
                if ((r2 == newRow - 1 && c2 == newCol - 1).not() && visited[newRow - 2][newCol - 2].not()) {
                    visited[newRow - 2][newCol - 2] = true
                    queue.add(Triple(newRow - 2, newCol - 2, curCnt + 1))
                }
            }

            newRow = curRow

            newCol = curCol + 1
            if (isIn(newRow + 1, newCol + 1) && isIn(newRow + 2, newCol + 2)) {
                if ((r2 == newRow + 1 && c2 == newCol + 1).not() && visited[newRow + 2][newCol + 2].not()) {
                    visited[newRow + 2][newCol + 2] = true
                    queue.add(Triple(newRow + 2, newCol + 2, curCnt + 1))
                }
            }

            if (isIn(newRow - 1, newCol + 1) && isIn(newRow - 2, newCol + 2)) {
                if ((r2 == newRow - 1 && c2 == newCol + 1).not() && visited[newRow - 2][newCol + 2].not()) {
                    visited[newRow - 2][newCol + 2] = true
                    queue.add(Triple(newRow - 2, newCol + 2, curCnt + 1))
                }
            }

            newCol = curCol - 1
            if (isIn(newRow + 1, newCol - 1) && isIn(newRow + 2, newCol - 2)) {
                if ((r2 == newRow + 1 && c2 == newCol - 1).not() && visited[newRow + 2][newCol - 2].not()) {
                    visited[newRow + 2][newCol - 2] = true
                    queue.add(Triple(newRow + 2, newCol - 2, curCnt + 1))
                }
            }

            if (isIn(newRow - 1, newCol - 1) && isIn(newRow - 2, newCol - 2)) {
                if ((r2 == newRow - 1 && c2 == newCol - 1).not() && visited[newRow - 2][newCol - 2].not()) {
                    visited[newRow - 2][newCol - 2] = true
                    queue.add(Triple(newRow - 2, newCol - 2, curCnt + 1))
                }
            }
        }

        println(-1)
    }

    fun isIn(row: Int, col: Int): Boolean {
        return row in 0 until 10 && col in 0 until 9
    }
}

fun main() {
    Solution16509.solution()
}