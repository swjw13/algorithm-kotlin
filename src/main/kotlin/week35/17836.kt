package week35

private class Solution17836 {

    val board = mutableListOf<List<Int>>()
    val dx = listOf(-1, 0, 1, 0)
    val dy = listOf(0, -1, 0, 1)

    data class Point(
        val row: Int,
        val col: Int,
        val turn: Int = 0
    )

    fun bfs(
        n: Int,
        m: Int,
        maxTurn: Int,
        startRow: Int,
        startCol: Int,
        endRow: Int,
        endCol: Int,
        isTrans: Boolean
    ): Int? {
        val visited = List(n) { MutableList(m) { false } }
        val deque = ArrayDeque<Point>().apply {
            visited[startRow][startCol] = true
            add(Point(startRow, startCol, 0))
        }

        while (deque.isNotEmpty()) {
            val (curRow, curCol, curTurn) = deque.removeFirst()
            if (curTurn > maxTurn) continue
            if (curRow == endRow && curCol == endCol) return curTurn

            for (i in 0 until 4) {
                val newRow = curRow + dx[i]
                val newCol = curCol + dy[i]

                if (newRow in 0 until n && newCol in 0 until m && visited[newRow][newCol].not()) {
                    if (isTrans) {
                        visited[newRow][newCol] = true
                        deque.add(Point(newRow, newCol, curTurn + 1))
                    } else {
                        if (board[newRow][newCol] != 1) {
                            visited[newRow][newCol] = true
                            deque.add(Point(newRow, newCol, curTurn + 1))
                        }
                    }
                }
            }
        }

        return null
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, m, t) = readln().split(" ").map { it.toInt() }
        var swordPos: Point = Point(0, 0, 0)
        for (i in 0 until n) {
            val lst = readln().split(" ").map { it.toInt() }
            if (2 in lst) {
                swordPos = Point(i, lst.indexOf(2))
            }
            board.add(lst)
        }

        val first = bfs(n, m, t, 0, 0, n - 1, m - 1, false)
        val second = bfs(n, m, t, 0, 0, swordPos.row, swordPos.col, false)
        val third = bfs(n, m, t, swordPos.row, swordPos.col, n - 1, m - 1, true)

        if (second == null || third == null) {
            if (first == null) println("Fail")
            else println(first)
        } else {
            if (first == null) if (second + third <= t) println(third + second) else println("Fail")
            else if (minOf(second + third, first) <= t) println(minOf(second + third, first)) else println("Fail")
        }
    }
}

fun main() {
    Solution17836().solution()
}