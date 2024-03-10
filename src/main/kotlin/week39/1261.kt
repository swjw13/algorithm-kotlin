package week39

private class Solution1261 {

    fun solution() = with(System.`in`.bufferedReader()) {
        val dxdy = listOf(
            listOf(-1, 0),
            listOf(1, 0),
            listOf(0, 1),
            listOf(0, -1)
        )

        val (n, m) = readln().split(" ").map { it.toInt() }
        val board = mutableListOf<List<Int>>()
        for (i in 0 until m) {
            val line = readln().toList().map { it.toString().toInt() }
            board.add(line)
        }

        val visited = List(m) { MutableList(n) { n * m + 1 } }
        val queue = ArrayDeque<List<Int>>().apply {
            add(listOf(0, 0, 0))
            visited[0][0] = 0
        }

        while (queue.isNotEmpty()) {
            val (curRow, curCol, curCnt) = queue.removeFirst()

            if (curCnt > visited[curRow][curCol]) continue

            for ((dx, dy) in dxdy){
                val newRow = curRow + dx
                val newCol = curCol + dy

                if (newRow in 0 until m && newCol in 0 until n){
                    val newCnt = curCnt + board[newRow][newCol]
                    if (visited[newRow][newCol] > curCnt + board[newRow][newCol]){
                        if (board[newRow][newCol] == 1){
                            queue.add(listOf(newRow, newCol, newCnt))
                        } else {
                            queue.addFirst(listOf(newRow, newCol, newCnt))
                        }
                        visited[newRow][newCol] = newCnt
                    }
                }
            }
        }
        println(visited[m - 1][n - 1])
    }
}

fun main() {
    Solution1261().solution()
}