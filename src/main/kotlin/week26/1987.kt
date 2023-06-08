package week26

private class Solution1987 {
    private val dxdy = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, 1), Pair(0, -1))
    private val board = mutableListOf<String>()
    private val visited = MutableList(26) { false }
    private var result = 0

    private fun getIndex(word: Char): Int {
        return word.code - 'A'.code
    }

    private fun dfs(row: Int, col: Int, turn: Int) {
        result = maxOf(result, turn)
        visited[getIndex(board[row][col])] = true
        for ((dx, dy) in dxdy) {
            val newRow = row + dx
            val newCol = col + dy
            if (newRow in 0 until board.size && newCol in 0 until board[0].length) {
                if (visited[getIndex(board[newRow][newCol])].not()) {
                    dfs(newRow, newCol, turn + 1)
                    visited[getIndex(board[newRow][newCol])] = false
                }
            }
        }
    }

    fun solution() = with(System.`in`.bufferedReader()) {
        val (n, _) = readln().split(" ").map { it.toInt() }
        for (i in 0 until n) {
            val line = readln().trim()
            board.add(line)
        }

        dfs(0, 0, 1)
        println(result)

    }
}

fun main() {
    Solution1987().solution()
}